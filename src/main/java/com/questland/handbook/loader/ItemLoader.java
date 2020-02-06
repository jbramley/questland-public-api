package com.questland.handbook.loader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questland.handbook.ItemRepository;
import com.questland.handbook.loader.model.PrivateItem;
import com.questland.handbook.model.Emblem;
import com.questland.handbook.model.Item;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemLoader implements ApplicationRunner {

  private final PrivateConverterService privateConverter;
  private final ItemRepository itemRepository;
  private final RestTemplate restTemplate = new RestTemplate();
  private final String latestTokenUrl =
      "http://gs-bhs-wrk-02.api-ql.com/client/checkstaticdata/?lang=en&graphics_quality=hd_android";
  private final String itemUrl =
      "http://gs-bhs-wrk-01.api-ql.com/staticdata/key/en/android/%s/item_templates/";
  private final String setEmblemUrl =
      "http://gs-bhs-wrk-01.api-ql.com/staticdata/key/en/android/%s/wearable_sets/";

  @Override
  public void run(ApplicationArguments args) throws Exception {
    String latestTokenResponse = restTemplate.getForObject(latestTokenUrl, String.class);

    String latestToken = new ObjectMapper().readTree(latestTokenResponse)
        .path("data")
        .path("static_data")
        .path("crc_details")
        .path("item_templates").asText();
    log.info("Latest item token is: " + latestToken);

    List<PrivateItem> privateItems = Arrays.asList(
        restTemplate.getForObject(String.format(itemUrl, latestToken), PrivateItem[].class));
    log.info("# of items discovered: " + privateItems.size());

    Map<Integer, Emblem> emblemMap = getEmblemMap();

    Set<String> validItemTypes = Set.of(
        "head",
        "chest",
        "gloves",
        "feet",
        "amulet",
        "ring",
        "talisman",
        "main_hand",
        "off_hand");

    List<Item> items = privateItems.stream()
        // Filter out any item that wouldn't be considered gear
        .filter(item -> validItemTypes.contains(item.getItemType()))
        // Convert to our internal gear model
        .map(item -> privateConverter.covertItemFromPrivate(item, emblemMap))

        .collect(Collectors.toList());

    log.info("Loading " + items.size() + " items into database...");
    itemRepository.saveAll(items);
    log.info("Database load of " + itemRepository.count() + " items complete.");

  }


  private Map<Integer, Emblem> getEmblemMap() {
    String latestTokenResponse = restTemplate.getForObject(latestTokenUrl, String.class);
    Map<Integer, Emblem> emblemMap = new HashMap<>();
    try {
      String emblemToken = new ObjectMapper().readTree(latestTokenResponse)
          .path("data")
          .path("static_data")
          .path("crc_details")
          .path("wearable_sets").asText();
      log.info("Latest set/emblem token is: " + emblemToken);

      String emblemUrl = String.format(setEmblemUrl, emblemToken);
      log.info(emblemUrl);
      String emblemDataRaw =
          restTemplate.getForObject(emblemUrl, String.class);

      JsonNode emblemArray = new ObjectMapper().readTree(emblemDataRaw);
      for (JsonNode emblemEntry : emblemArray) {
        Iterable<JsonNode> iterable = emblemEntry::elements;
        List<JsonNode> emblemEntryList =
            StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        emblemMap.put(
            emblemEntryList.get(0).asInt(),
            PrivateConverterService.getEmblemFromPrivate(emblemEntryList.get(1).asText()));
      }
      return emblemMap;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}

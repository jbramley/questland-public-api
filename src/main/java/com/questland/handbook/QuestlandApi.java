package com.questland.handbook;

import com.questland.handbook.livequery.GuildBossQueryService;
import com.questland.handbook.model.GuildBoss;
import com.questland.handbook.model.Item;
import com.questland.handbook.model.Orb;
import com.questland.handbook.model.Quality;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class QuestlandApi {

  private final ItemRepository itemRepository;
  private final OrbRepository orbRepository;
  private final GuildBossQueryService guildBossQueryService;

  @GetMapping("/items")
  public List<Item> getItems(Sort sort,
                             @RequestParam(value = "filterArtifacts", defaultValue = "false") boolean filterArtifacts) {
    if (filterArtifacts) {
      return itemRepository.findAllByQualityIn(
          Set.of(
              Quality.COMMON,
              Quality.UNCOMMON,
              Quality.RARE,
              Quality.EPIC,
              Quality.LEGENDARY
          ),
          sort);
    } else {
      return itemRepository.findAll(sort);
    }
  }

  @GetMapping("/items/{id}")
  public Item getItemById(@PathVariable("id") long id) {
    return itemRepository.findById(id).orElse(null);
  }

  @GetMapping("/items/name/{name}")
  public Item getItemByName(@PathVariable("name") String name,
                            @RequestParam(value = "quality", required = false) Quality quality) {
    List<Item> itemsByName = itemRepository.findByNameIgnoreCase(name);
    // This logic assists with filtering for specific artifacts
    if (quality != null) {
      itemsByName = itemsByName.stream()
          .filter(item -> item.getQuality().equals(quality))
          .collect(Collectors.toList());
      // This logic makes sure we select legendary over artifact if no quality was specified
    } else if (itemsByName.size() > 1) {
      itemsByName = itemsByName.stream()
          .filter(item -> item.getQuality().equals(Quality.LEGENDARY))
          .collect(Collectors.toList());
    }
    return itemsByName.stream().findFirst().orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Item was not found."));
  }

  @GetMapping("/orbs")
  public List<Orb> getOrbs(Sort sort) {
    return orbRepository.findAll(sort);
  }

  @GetMapping("/orbs/{id}")
  public Orb getOrbById(@PathVariable("id") long id) {
    return orbRepository.findById(id).orElse(null);
  }

  @GetMapping("/orbs/name/{name}")
  public Orb getOrbByName(@PathVariable("name") String name) {
    return orbRepository.findByNameIgnoreCase(name);
  }

  @GetMapping("/guildboss/current")
  public GuildBoss getCurrentGuildBoss() {
    GuildBoss currentGuildBoss = guildBossQueryService.getCurrentGuildBoss();
    return currentGuildBoss != null
           ? currentGuildBoss
           : new GuildBoss("Couldn't find current guild boss");
  }
}
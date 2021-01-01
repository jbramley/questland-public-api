package com.questland.handbook.publicmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class DisplayableBuild {
  @JsonIgnore
  private Build build;
  private String name;
  private String description;
  private String mainHandWeapon;
  private String offHandWeapon;
  @Getter(lazy=true) private final String weapons = mkWeapons();
  @Singular private List<String> mainHandAlternativeWeapons;
  private String mainHandPassive;
  @Getter(lazy=true) private final String mainHandAlternatives = mkMainHandAlternatives();
  @Singular private List<String> offHandAlternativeWeapons;
  private String offHandPassive;
  @Getter(lazy=true) private final String offHandAlternatives = mkOffHandAlternatives();
  private String talent1;
  private String talent2;
  private String talent3;
  private String links;
  private String videoGuide;
  private String image;

  private String mkWeapons() {
    return this.mainHandWeapon + ", " + this.offHandWeapon;
  }

  private String mkMainHandAlternatives() {
    if (this.mainHandAlternativeWeapons == null || this.mainHandAlternativeWeapons.size() == 0) {
      return this.mainHandPassive + ": None";
    }
    return this.mainHandPassive + ": " + String.join(", ", this.mainHandAlternativeWeapons);
  }

  private String mkOffHandAlternatives() {
    if (this.offHandAlternativeWeapons == null || this.offHandAlternativeWeapons.size() == 0) {
      return this.offHandPassive + ": None";
    }
    return this.offHandPassive + ": " + String.join(", ", this.offHandAlternativeWeapons);
  }
}

package com.questland.handbook.flatbuffers;// automatically generated by the FlatBuffers compiler, do not modify

import com.google.flatbuffers.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class LootChancesGroup extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_1_12_0(); }
  public static LootChancesGroup getRootAsLootChancesGroup(ByteBuffer _bb) { return getRootAsLootChancesGroup(_bb, new LootChancesGroup()); }
  public static LootChancesGroup getRootAsLootChancesGroup(ByteBuffer _bb, LootChancesGroup obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public LootChancesGroup __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public byte itemGroup() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte qualities(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int qualitiesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector qualitiesVector() { return qualitiesVector(new ByteVector()); }
  public ByteVector qualitiesVector(ByteVector obj) { int o = __offset(6); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer qualitiesAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer qualitiesInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  public float chances(int j) { int o = __offset(8); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int chancesLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public FloatVector chancesVector() { return chancesVector(new FloatVector()); }
  public FloatVector chancesVector(FloatVector obj) { int o = __offset(8); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer chancesAsByteBuffer() { return __vector_as_bytebuffer(8, 4); }
  public ByteBuffer chancesInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 4); }

  public static int createLootChancesGroup(FlatBufferBuilder builder,
      byte item_group,
      int qualitiesOffset,
      int chancesOffset) {
    builder.startTable(3);
    LootChancesGroup.addChances(builder, chancesOffset);
    LootChancesGroup.addQualities(builder, qualitiesOffset);
    LootChancesGroup.addItemGroup(builder, item_group);
    return LootChancesGroup.endLootChancesGroup(builder);
  }

  public static void startLootChancesGroup(FlatBufferBuilder builder) { builder.startTable(3); }
  public static void addItemGroup(FlatBufferBuilder builder, byte itemGroup) { builder.addByte(0, itemGroup, 0); }
  public static void addQualities(FlatBufferBuilder builder, int qualitiesOffset) { builder.addOffset(1, qualitiesOffset, 0); }
  public static int createQualitiesVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createQualitiesVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startQualitiesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addChances(FlatBufferBuilder builder, int chancesOffset) { builder.addOffset(2, chancesOffset, 0); }
  public static int createChancesVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startChancesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endLootChancesGroup(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public LootChancesGroup get(int j) { return get(new LootChancesGroup(), j); }
    public LootChancesGroup get(LootChancesGroup obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}


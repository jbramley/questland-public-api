package com.questland.handbook.flatbuffers;// automatically generated by the FlatBuffers compiler, do not modify

import com.google.flatbuffers.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class Talent extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_1_12_0(); }
  public static Talent getRootAsTalent(ByteBuffer _bb) { return getRootAsTalent(_bb, new Talent()); }
  public static Talent getRootAsTalent(ByteBuffer _bb, Talent obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Talent __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int preview() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int back() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int stars() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public long extrTpl() { int o = __offset(10); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }
  public int extrAmount() { int o = __offset(12); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int s(int j) { int o = __offset(14); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int sLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public IntVector sVector() { return sVector(new IntVector()); }
  public IntVector sVector(IntVector obj) { int o = __offset(14); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer sAsByteBuffer() { return __vector_as_bytebuffer(14, 4); }
  public ByteBuffer sInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 14, 4); }

  public static int createTalent(FlatBufferBuilder builder,
      int preview,
      int back,
      int stars,
      long extr_tpl,
      int extr_amount,
      int sOffset) {
    builder.startTable(6);
    Talent.addS(builder, sOffset);
    Talent.addExtrAmount(builder, extr_amount);
    Talent.addExtrTpl(builder, extr_tpl);
    Talent.addStars(builder, stars);
    Talent.addBack(builder, back);
    Talent.addPreview(builder, preview);
    return Talent.endTalent(builder);
  }

  public static void startTalent(FlatBufferBuilder builder) { builder.startTable(6); }
  public static void addPreview(FlatBufferBuilder builder, int preview) { builder.addInt(0, preview, 0); }
  public static void addBack(FlatBufferBuilder builder, int back) { builder.addInt(1, back, 0); }
  public static void addStars(FlatBufferBuilder builder, int stars) { builder.addInt(2, stars, 0); }
  public static void addExtrTpl(FlatBufferBuilder builder, long extrTpl) { builder.addInt(3, (int)extrTpl, (int)0L); }
  public static void addExtrAmount(FlatBufferBuilder builder, int extrAmount) { builder.addInt(4, extrAmount, 0); }
  public static void addS(FlatBufferBuilder builder, int sOffset) { builder.addOffset(5, sOffset, 0); }
  public static int createSVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startSVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endTalent(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Talent get(int j) { return get(new Talent(), j); }
    public Talent get(Talent obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}


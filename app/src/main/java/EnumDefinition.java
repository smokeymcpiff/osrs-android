import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("iw")
@Implements("EnumDefinition")
public class EnumDefinition extends DualNode {
	@ObfuscatedName("t")
	@ObfuscatedSignature(
		signature = "Leh;"
	)
	@Export("EnumDefinition_cached")
	static EvictingDualNodeHashTable EnumDefinition_cached;
	@ObfuscatedName("o")
	@Export("inputType")
	public char inputType;
	@ObfuscatedName("e")
	@Export("outputType")
	public char outputType;
	@ObfuscatedName("i")
	@Export("defaultStr")
	public String defaultStr;
	@ObfuscatedName("g")
	@ObfuscatedGetter(
		intValue = 763609869
	)
	@Export("defaultInt")
	public int defaultInt;
	@ObfuscatedName("d")
	@ObfuscatedGetter(
		intValue = 1620263743
	)
	@Export("outputCount")
	public int outputCount;
	@ObfuscatedName("l")
	@Export("keys")
	public int[] keys;
	@ObfuscatedName("j")
	@Export("intVals")
	public int[] intVals;
	@ObfuscatedName("m")
	@Export("strVals")
	public String[] strVals;

	EnumDefinition() {
		this.defaultStr = "null";
		this.outputCount = 0;
	}

	@ObfuscatedName("t")
	@ObfuscatedSignature(
		signature = "(Lkp;I)V",
		garbageValue = "-1295672156"
	)
	@Export("decode")
	void decode(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.decodeNext(var1, var2);
		}
	}

	@ObfuscatedName("o")
	@ObfuscatedSignature(
		signature = "(Lkp;II)V",
		garbageValue = "1239403484"
	)
	@Export("decodeNext")
	void decodeNext(Buffer var1, int var2) {
		if (var2 == 1) {
			this.inputType = (char)var1.readUnsignedByte();
		} else if (var2 == 2) {
			this.outputType = (char)var1.readUnsignedByte();
		} else if (var2 == 3) {
			this.defaultStr = var1.readStringCp1252NullTerminated();
		} else if (var2 == 4) {
			this.defaultInt = var1.readInt();
		} else {
			int var3;
			if (var2 == 5) {
				this.outputCount = var1.readUnsignedShort();
				this.keys = new int[this.outputCount];
				this.strVals = new String[this.outputCount];

				for (var3 = 0; var3 < this.outputCount; ++var3) {
					this.keys[var3] = var1.readInt();
					this.strVals[var3] = var1.readStringCp1252NullTerminated();
				}
			} else if (var2 == 6) {
				this.outputCount = var1.readUnsignedShort();
				this.keys = new int[this.outputCount];
				this.intVals = new int[this.outputCount];

				for (var3 = 0; var3 < this.outputCount; ++var3) {
					this.keys[var3] = var1.readInt();
					this.intVals[var3] = var1.readInt();
				}
			}
		}

	}

	@ObfuscatedName("e")
	@ObfuscatedSignature(
		signature = "(I)I",
		garbageValue = "-1222306007"
	)
	@Export("size")
	public int size() {
		return this.outputCount;
	}

	@ObfuscatedName("p")
	@ObfuscatedSignature(
		signature = "(II)Les;",
		garbageValue = "-1471944994"
	)
	@Export("getFrames")
	static Frames getFrames(int var0) {
		Frames var1 = (Frames)SequenceDefinition.SequenceDefinition_cachedFrames.get((long)var0);
		if (var1 != null) {
			return var1;
		} else {
			AbstractArchive var2 = Canvas.SequenceDefinition_animationsArchive;
			AbstractArchive var3 = SequenceDefinition.SequenceDefinition_skeletonsArchive;
			boolean var4 = true;
			int[] var5 = var2.getGroupFileIds(var0);

			for (int var6 = 0; var6 < var5.length; ++var6) {
				byte[] var7 = var2.getFile(var0, var5[var6]);
				if (var7 == null) {
					var4 = false;
				} else {
					int var8 = (var7[0] & 255) << 8 | var7[1] & 255;
					byte[] var9 = var3.getFile(var8, 0);
					if (var9 == null) {
						var4 = false;
					}
				}
			}

			Frames var11;
			if (!var4) {
				var11 = null;
			} else {
				try {
					var11 = new Frames(var2, var3, var0, false);
				} catch (Exception var10) {
					var11 = null;
				}
			}

			if (var11 != null) {
				SequenceDefinition.SequenceDefinition_cachedFrames.put(var11, (long)var0);
			}

			return var11;
		}
	}

	static {
		EnumDefinition_cached = new EvictingDualNodeHashTable(64);
	}
}

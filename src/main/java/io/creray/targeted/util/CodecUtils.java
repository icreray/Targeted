package io.creray.targeted.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class CodecUtils {

    public Codec<Float> floatRangeExclusive(float min, float max) {
        return Codec.FLOAT.validate(checkRangeExclusive(min, max));
    }

    public <N extends Number & Comparable<N>> Function<N, DataResult<N>> checkRangeExclusive(
        N min, N max
    ) {
        return value -> value.compareTo(min) > 0 && value.compareTo(max) < 0
            ? DataResult.success(value)
            : DataResult.error(() -> "Value " + value + " outside of range (" + min + ":" + max + ")");
    }
}

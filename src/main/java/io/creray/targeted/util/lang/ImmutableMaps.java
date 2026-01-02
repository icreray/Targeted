package io.creray.targeted.util.lang;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ImmutableMaps {

    public <K, V1, V2> Map<K, V2> transformValues(Map<K, V1> map, Function<V1, V2> transformer) {
        return map.entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                Map.Entry::getKey,
                e -> transformer.apply(e.getValue())
            ));
    }
}

package ru.noxly.websocket.utils;

import java.util.function.Function;

import static java.util.Optional.ofNullable;

public class CommonUtils {

	public static <R, S> R nullOrApply(S source, Function<S, R> mapper){
		return ofNullable(source)
				.map(mapper)
				.orElse(null);
	}
}

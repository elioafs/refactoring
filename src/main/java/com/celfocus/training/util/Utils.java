package com.celfocus.training.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Hex;

import com.celfocus.training.util.exception.RefactorigException;

public final class Utils {

	private Utils() {
	}

	private static Logger logger = Logger.getLogger(Utils.class.getName());

	private static MessageDigest sha256;

	static {
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			logger.severe("Error creating SHA-256");
		}
	}

	public static String toHexStringSHA256(String source, Charset charset) {
		return Hex.encodeHexString(toSHA256(source.getBytes(charset)));
	}

	public static byte[] toSHA256(byte[] bytes) {
		return sha256.digest(bytes);
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static Map<String, String> parseHTTPHeaderMap(String headers) {
		String value = headers.substring(1, headers.length() - 1);
		String[] keyValuePairs = value.split(",");
		Map<String, String> map = new HashMap<>();
		for (String pair : keyValuePairs) {
			String[] entry = pair.split("=", 2);
			if (entry.length > 1) {
				map.put(entry[0].trim(), entry[1].trim());
			}
		}
		return map;
	}

	public static Date toDate(String date, DateFormat format) throws RefactorigException {
		Objects.requireNonNull(date);
		Objects.requireNonNull(format);
		try {
			return format.parse(date);
		} catch (ParseException ex) {
			throw new RefactorigException(ex);
		}
	}

	public static String toString(Date date, String format) {
		return toString(date, new SimpleDateFormat(format));
	}

	public static String toString(Date date, DateFormat format) {
		Objects.requireNonNull(date);
		Objects.requireNonNull(format);
		return format.format(date);
	}

	public static <T> boolean isNullOrEmpty(List<T> list) {
		return list == null || list.isEmpty();
	}

	@SafeVarargs
	public static <T> java.util.List<T> createListFromArray(T... ts) {
		if (ts == null) {
			return new ArrayList<>(0);
		}
		List<T> list = new ArrayList<>(ts.length);
		for (T t : ts) {
			list.add(t);
		}
		return list;
	}

	@SafeVarargs
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> createMapFromArray(Object... ts) {
		if (ts == null) {
			return new HashMap<>(0);
		}
		if (ts.length % 2 != 0) {
			throw new IllegalArgumentException("Length should be pair");
		}
		int length = ts.length / 2;
		Map<K, V> map = new HashMap<>(length);
		for (int index = 0; index <= length; index += 2) {
			map.put((K) ts[index], (V) ts[index + 1]);
		}
		return map;
	}

	public static int yearsSinceDate(Date date) {
		LocalDate startDate = Utils.convertToLocalDate(date);
		LocalDate today = LocalDate.now();
		return Period.between(startDate, today).getYears();
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static String convertItemName(String itemName) {
		Objects.requireNonNull(itemName);
		return itemName.toLowerCase().concat("_item");
	}

}
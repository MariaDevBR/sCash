package com.maria.cash.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;

import com.maria.cash.Main;

public class Format {

	private static Main main = Main.getPlugin(Main.class);

	private static final Pattern PATTERN = Pattern.compile("^(\\d+\\.?\\d*)(\\D+)");

	private static FileConfiguration config = main.getConfig();

	private static List<String> suffixes;

	static {
		suffixes = config.getStringList("Formatacao");
	}

	public static void changeSuffixes(List<String> suffixes) {
		Format.suffixes = suffixes;
	}

	public static String formatNumber(double value) {
		int index = 0;
		double tmp;

		while ((tmp = value / 1000) >= 1) {
			value = tmp;
			++index;

		}
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		return decimalFormat.format(value) + suffixes.get(index);
	}

	public static double parseString(String value) throws Exception {
		try {
			return Double.parseDouble(value);

		} catch (Exception ignored) {

		}
		Matcher matcher = PATTERN.matcher(value);
		if (!matcher.find())
			throw new Exception("Invalid format");

		double amount = Double.parseDouble(matcher.group(1));
		String suffix = matcher.group(2);

		int index = suffixes.indexOf(suffix.toUpperCase());

		return amount * Math.pow(1000, index);
	}

}
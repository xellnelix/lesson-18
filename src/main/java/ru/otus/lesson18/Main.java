package ru.otus.lesson18;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		File file = new File(".\\files\\");
		System.out.println(Arrays.toString(file.list()));

		System.out.println("Введите название файла для работы с ним:");
		Scanner scanner = new Scanner(System.in);
		String inputFileName = scanner.nextLine();
		if (readFromFile(inputFileName)) {
			System.out.println("\nВведите строку для записи в файл:");
			String data = scanner.nextLine();
			writeToFile(inputFileName, data);
		}
	}


	public static boolean readFromFile (String fileName) {
		try (InputStreamReader in = new InputStreamReader(new FileInputStream ("files\\" + fileName))) {
			int n = in.read();
			while (n != -1) {
				System.out.print((char) n);
				n = in.read();
			}
			return true;
		} catch (IOException e) {
			System.out.println("Не существует такого файла");
			return false;
		}
	}

	public static void writeToFile (String fileName, String data) {
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("files\\" + fileName))) {
			byte[] buffer = data.getBytes(StandardCharsets.UTF_8);
			for (int i = 0; i < buffer.length; i++) {
				out.write(buffer[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
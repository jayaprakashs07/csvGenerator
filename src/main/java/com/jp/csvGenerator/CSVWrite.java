package com.jp.csvGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

@Service
public class CSVWrite {

    public void WritingToCSV () throws IOException {
        //Instantiating the CSVWriter class
        CSVWriter writer = new CSVWriter(new FileWriter("/home/jayaprakash/Desktop/workspace/sampleTemp.csv"));

        List list = new ArrayList();
        //Writing data to a csv file
        String line1[] = {"seuci", "brand", "firstname", "lastname", "dateofbirth", "gender", "country", "istoreid",
                "isourceid", "irecipientstatus", "haspreference"};
        list.add(line1);
        int length=1000;

        for (int i=1; i<length; i++) {

            String data[] = { UUID.randomUUID().toString(), "ADI", randomString(), "", randomDate().toString() ,
                    randomGenderPic(),  "IN", "2548", "0", "1", randomPreference()};
            //Instantiating the List Object
            list.add(data);
            //Writing data to the csv file
        }
        writer.writeAll(list);
        writer.flush();
        System.out.println("Data entered");
    }

    private static String randomGenderPic() {
        String[] genArr = {"male", "female", ""};
        Random generator = new Random();
        int randomIndex = generator.nextInt(genArr.length);
        return genArr[randomIndex];
    }
    private static String randomPreference() {
        String[] genArr = {"1", ""};
        Random generator = new Random();
        int randomIndex = generator.nextInt(genArr.length);
        return genArr[randomIndex];
    }

    private static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    private static LocalDate randomDate() {
        LocalDate startDate = LocalDate.of(1990, 1, 1); //start date
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.of(2002, 1, 1); //end date
        long end = endDate.toEpochDay();

        return LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());

    }

}

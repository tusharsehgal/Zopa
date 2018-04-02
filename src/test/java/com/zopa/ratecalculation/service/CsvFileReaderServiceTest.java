package com.zopa.ratecalculation.service;


import com.zopa.ratecalculation.exception.NoOffersAvailableException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CsvFileReaderServiceTest {

    CsvFileReaderService fileReaderService = new CsvFileReaderService();

    @Test
    public void assertNoOfRowsInInputCSVFile() throws IOException {
        //Given
        String filePath = "src\\test\\resources\\MarketData.csv";

        //When
        fileReaderService.readAndProcessFile(filePath).size();

        //Then
        assertEquals(7, fileReaderService.readAndProcessFile(filePath).size());
    }

    @Test(expected = NoOffersAvailableException.class)
    public void throwsExceptionIfNoOffersAvailableInCsvFile() throws IOException {
        //Given
        String filePath = "src\\test\\resources\\MarketData_NoOffers.csv";

        //When
        fileReaderService.readAndProcessFile(filePath);

        //Then
        //Throws NoOffersAvailableException
    }

    @Test(expected = IOException.class)
    public void throwsExceptionForIncorrectFilePath() throws IOException {
        //Given
        String filePath = "src\\test\\resources\\Market.csv";

        //When
        fileReaderService.readAndProcessFile(filePath);

        //Then
        //Throws IO exception
    }

}
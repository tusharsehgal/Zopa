package com.zopa.ratecalculation.service;

import com.zopa.ratecalculation.contract.FileReaderService;
import com.zopa.ratecalculation.exception.NoOffersAvailableException;
import com.zopa.ratecalculation.model.Offer;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads the csv file and process the data into a list.
 */
public class CsvFileReaderService implements FileReaderService {

    /**
     * Read the csv file and process it.
     *
     * @param filePath path of file to read and process it
     * @return list of all available of
     * @throws IOException
     */
    @Override
    public List<Offer> readAndProcessFile(final String filePath) throws IOException {
        List<Offer> offers;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            offers = processFile(lines);
            if (offers.size() == 0) {
                throw new NoOffersAvailableException("No offers available to provide loan");
            }
        } catch (IOException e) {
            throw e;
        }
        return offers;
    }

    /**
     * Process CSV file.
     *
     * @param lines stream of lines of the input file
     * @return list of all available offers
     */
    private List<Offer> processFile(final Stream<String> lines) {
        List<Offer> listOfOffers;
        listOfOffers = lines.skip(1).map(line -> mapToOffer(line))
                .sorted()
                .collect(Collectors.toList());
        return listOfOffers;
    }

    /**
     * For every row of input CSV file,create the available offer.
     *
     * @param line take one line at a time from the input csv file
     * @return offer object after setting all the values taken
     */
    private Offer mapToOffer(final String line) {
        if (StringUtils.isNotEmpty(line)) {
            String[] arrLine = line.split(",");
            return new Offer(arrLine[0], Double.parseDouble(arrLine[1]), Integer.parseInt(arrLine[2]));
        }
        return null;
    }
}

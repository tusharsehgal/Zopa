package com.zopa.ratecalculation.contract;

import com.zopa.ratecalculation.model.Offer;
import java.io.IOException;
import java.util.List;

public interface FileReaderService {
    /**
     * Read and process a file
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    List<Offer> readAndProcessFile(String filePath) throws IOException;
}

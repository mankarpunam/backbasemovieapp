package com.example.backbase.service;

import com.example.backbase.repository.MovieDetailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailServiceImpl implements MovieDetailService{
    private static final Logger logger = LogManager.getLogger(MovieDetailServiceImpl.class);
    @Autowired
    MovieDetailRepository movieDetailRepository;
    @Override
    public String getBetsOscarMovie() {
        logger.info("Fetching movie details by best picture ");
        return null;//movieDetailRepository.findByWon();
    }
}

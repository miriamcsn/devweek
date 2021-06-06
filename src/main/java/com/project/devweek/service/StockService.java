package com.project.devweek.service;

import com.project.devweek.exceptions.BusinessException;
import com.project.devweek.exceptions.NotFoundException;
import com.project.devweek.mapper.StockMapper;
import com.project.devweek.model.Stock;
import com.project.devweek.model.dto.stockDTO;
import com.project.devweek.repository.StockRepository;
import com.project.devweek.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {


    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public stockDTO save(stockDTO dto) {

        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    @Transactional
    public stockDTO update(stockDTO dto) {

        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);

    }

    @Transactional
    public List<stockDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional
    public stockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public stockDTO delete(Long id) {
        stockDTO dto = this.findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }

    @Transactional
    public List<stockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);


    }
}

package com.paic.ussd.repository;

import com.paic.ussd.entity.CallDetailRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallDetailRecordsRepository extends JpaRepository<CallDetailRecords, Integer> {
}

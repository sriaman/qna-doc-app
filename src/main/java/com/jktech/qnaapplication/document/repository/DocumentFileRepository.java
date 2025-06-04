package com.jktech.qnaapplication.document.repository;

import com.jktech.qnaapplication.document.model.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, Long> {
}

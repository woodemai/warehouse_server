package com.ru.vsu.woodemai.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, String> {
}

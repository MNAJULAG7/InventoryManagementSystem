package com.inventory.supplier.repositoy;

import com.inventory.supplier.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {

}

package dad.vuelanding.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Hotel;

public interface hotelRepository extends JpaRepository<Hotel, Long>{

}

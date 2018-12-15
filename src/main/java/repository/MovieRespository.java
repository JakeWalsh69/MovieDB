package repository;

import controller.Movie;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@ComponentScan({"movie", "repository", "controller"})
public interface MovieRespository extends JpaRepository<Movie, Long> {
}

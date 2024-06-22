package nhom9.gym.dao;

import nhom9.gym.entity.Equipment;
import nhom9.gym.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "room")
public interface RoomRepository extends JpaRepository<Room, Long> {
    public Room findByRoomId(Long id);
}

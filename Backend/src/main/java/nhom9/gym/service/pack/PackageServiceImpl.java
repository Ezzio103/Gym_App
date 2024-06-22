package nhom9.gym.service.pack;

import nhom9.gym.dao.PackRepository;
import nhom9.gym.entity.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PackageServiceImpl  implements PackageService{
    @Autowired
    private PackRepository packRepository;

    @Override
    public Pack findByPackageId(Long id) {
        Pack pack = packRepository.findByPackId(id);
        return pack;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Pack pack = packRepository.findByPackId(id);
        if(pack==null){
            return ResponseEntity.badRequest().build();
        }
        packRepository.deleteById(id);
        return ResponseEntity.ok("da xoa pack thanh cong");
    }
}

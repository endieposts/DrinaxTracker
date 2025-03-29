package com.endie.drinaxtracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;
import com.endie.drinaxtracker.model.StarSystem;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class StarSystemRepositoryTest {

    @Autowired
    StarSystemRepository starSystemRepository;

    @Test
    public void should_find_no_starSystems_if_repository_is_empty() {
        starSystemRepository.deleteAll();

        Iterable<StarSystem> starSystems = starSystemRepository.findAll();

        assertThat(starSystems).isEmpty();
    }

    @Test
    public void should_store_a_starSystem() {
        StarSystem starSystem = starSystemRepository.save(new StarSystem( "name1", "notes1", "sector1", "subSector1", "0", "0"));

        //assertThat(starSystem).hasFieldOrPropertyWithValue("id", 1);
        assertThat(starSystem).hasFieldOrPropertyWithValue("notes", "notes1");
    }

    @Test
    public void should_find_all_starSystems() {
        StarSystem starSystem1 = starSystemRepository.save(new StarSystem("name2", "notes2", "sector2", "subSector2", "0", "0"));
        StarSystem starSystem2 = starSystemRepository.save(new StarSystem("name3", "notes3", "sector3", "subSector3", "0", "0"));
        StarSystem starSystem3 = starSystemRepository.save(new StarSystem("name4", "notes4", "sector4", "subSector4", "0", "0"));

        Iterable<StarSystem> AppStarSystems = starSystemRepository.findAll();

        assertThat(AppStarSystems).contains(starSystem1, starSystem2, starSystem3);
    }

    @Test
    public void should_find_starSystem_by_id() {
        StarSystem starSystem = starSystemRepository.save(new StarSystem("name5", "notes5", "sector5", "subSector5", "0", "0"));
        StarSystem starSystem2 = starSystemRepository.save(new StarSystem("name6", "notes6", "sector6", "subSector6", "0", "0"));

        StarSystem foundStarSystem = starSystemRepository.findById(starSystem2.getId()).get();

        assertThat(foundStarSystem).isEqualTo(starSystem2);
    }

    @Test
    public void should_update_starSystem_by_id() {
        StarSystem starSystem = starSystemRepository.save(new StarSystem("name7", "notes7", "sector7", "subSector7", "0", "0"));
        StarSystem starSystem2 = starSystemRepository.save(new StarSystem("name8", "notes8", "sector8", "subSector8", "0", "0"));

        StarSystem updatedStarSystem = new StarSystem("name9", "notes9", "sector9", "subSector9", "0", "0");
        updatedStarSystem.setId(starSystem2.getId());
        StarSystem foundStarSystem = starSystemRepository.findById(starSystem2.getId()).get();
        foundStarSystem.setName(updatedStarSystem.getName());

        starSystemRepository.save(foundStarSystem);

        assertThat(foundStarSystem.getId()).isEqualTo(updatedStarSystem.getId());
    }

    @Test
    public void should_delete_starSystem_by_id() {
        StarSystem starSystem = starSystemRepository.save(new StarSystem("name10", "notes10", "sector10", "subSector10", "0", "0"));
        StarSystem starSystem2 = starSystemRepository.save(new StarSystem("name11", "notes11", "sector11", "subSector11", "0", "0"));

        starSystemRepository.delete(starSystem2);

        Iterable<StarSystem> AppStarSystems = starSystemRepository.findAll();

        assertThat(AppStarSystems).contains(starSystem);
        assertThat(AppStarSystems).doesNotContain(starSystem2);
    }

    @Test
    public void should_delete_all_users() {
        StarSystem starSystem = starSystemRepository.save(new StarSystem("name12", "notes12", "sector12", "subSector12", "0", "0"));
        StarSystem starSystem2 = starSystemRepository.save(new StarSystem("name13", "notes13", "sector13", "subSector13", "0", "0"));
        
        starSystemRepository.deleteAll();

        Iterable<StarSystem> starSystems = starSystemRepository.findAll();

        assertThat(starSystems).isEmpty();
    }
}

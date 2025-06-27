package com.example.training_animal_application.model;

import com.example.training_animal_application.model.error.ErrorException;
import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddHouseTest {
    @Test
    @DisplayName("SUCCESS：重量・超過なし(同値)：数・超過なし(同値)：動物なし")
    public void case1() {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(2));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(50));

        try {
            new AddHouse(cage, animal);
            assertEquals(1, cage.getAnimals().size());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    @DisplayName("SUCCESS：重量・超過なし(境界値)：数・超過なし：動物なし")
    public void case2() {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(110));
        cage.setLimitSize(new Size(2));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        try {
            var testSuite = new AddHouse(cage, animal);
            ;
            assertEquals(1, cage.getAnimals().size());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    @DisplayName("WARN：重量・制限超過(境界値)：数・超過なし：動物なし")
    public void case3() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(109));
        cage.setLimitSize(new Size(2));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        ;
        assertEquals(1, cage.getAnimals().size());
        assertEquals("制限重量を1Kg超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("WARN：重量・制限超過(同値)：数・超過なし：動物なし")
    public void case4() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(105));
        cage.setLimitSize(new Size(2));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        ;
        assertEquals(1, cage.getAnimals().size());
        assertEquals("制限重量を5Kg超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("WARN：重量・真制限超過なし(境界値)：数・超過なし：動物なし")
    public void case5() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(2));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(1, cage.getAnimals().size());
        assertEquals("制限重量を10Kg超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("ERROR：重量・真制限超過(同値)：数・超過なし：動物なし")
    public void case6() {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(2));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(110));

        var error = assertThrows(ErrorException.class, () -> new AddHouse(cage, animal));
        assertEquals(0, cage.getAnimals().size());
        assertEquals("制限重量を超えています。", error.getMessage());
    }

    //

    @Test
    @DisplayName("SUCCESS：重量・超過なし(境界値)：数・超過なし：動物あり")
    public void case7() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(330));
        cage.setLimitSize(new Size(3));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a1);
        var a2 = new Animal();
        a2.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a2);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(3, cage.getAnimals().size());
    }

    @Test
    @DisplayName("WARN：重量・制限超過(境界値)：数・超過なし：動物あり")
    public void case8() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(329));
        cage.setLimitSize(new Size(3));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a1);
        var a2 = new Animal();
        a2.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a2);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(3, cage.getAnimals().size());
        assertEquals("制限重量を1Kg超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("WARN：重量・制限超過(同値)：数・超過なし：動物あり")
    public void case9() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(325));
        cage.setLimitSize(new Size(3));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a1);
        var a2 = new Animal();
        a2.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a2);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(3, cage.getAnimals().size());
        assertEquals("制限重量を5Kg超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("WARN：重量・真制限超過なし(境界値)：数・超過なし：動物あり")
    public void case10() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(200));
        cage.setLimitSize(new Size(2));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a1);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(2, cage.getAnimals().size());
        assertEquals("制限重量を20Kg超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("ERROR：重量・真制限超過(同値)：数・超過なし：動物あり")
    public void case11() {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(200));
        cage.setLimitSize(new Size(2));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a1);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(110));


        var error = assertThrows(ErrorException.class, () -> new AddHouse(cage, animal));
        assertEquals(1, cage.getAnimals().size());
        assertEquals("制限重量を超えています。", error.getMessage());
    }

    //

    @Test
    @DisplayName("SUCCESS：重量・超過なし：数・超過なし(境界値)：動物なし")
    public void case12() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(1));

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(50));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(1, cage.getAnimals().size());
    }

    @Test
    @DisplayName("SUCCESS：重量・超過なし：数・超過なし(境界値)：動物あり")
    public void case13() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(3));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(25));
        cage.getAnimals().add(a1);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(25));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(2, cage.getAnimals().size());
    }

    @Test
    @DisplayName("WARN：重量・超過なし：数・制限超過あり(境界値)：動物あり")
    public void case14() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(1));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(25));
        cage.getAnimals().add(a1);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(25));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(2, cage.getAnimals().size());
        assertEquals("制限収容数を超えました。", testSuite.getMessage());
    }

    @Test
    @DisplayName("ERROR：重量・超過なし：数・真制限超過あり(境界値)：動物あり")
    public void case15() {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(100));
        cage.setLimitSize(new Size(2));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(10));
        cage.getAnimals().add(a1);
        var a2 = new Animal();
        a2.setWeight(new AnimalWeight(10));
        cage.getAnimals().add(a2);
        var a3 = new Animal();
        a3.setWeight(new AnimalWeight(10));
        cage.getAnimals().add(a3);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(25));

        var error = assertThrows(ErrorException.class, () -> new AddHouse(cage, animal));
        assertEquals(3, cage.getAnimals().size());
        assertEquals("制限収容数を超えています。", error.getMessage());
    }

    @Test
    @DisplayName("ERROR：重量・超過あり：数・制限超過あり：動物あり")
    public void case16() {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(310));
        cage.setLimitSize(new Size(2));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a1);
        var a2 = new Animal();
        a2.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a2);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(100));

        var error = assertThrows(ErrorException.class, () -> new AddHouse(cage, animal));
        assertEquals(2, cage.getAnimals().size());
        assertEquals("制限重量・制限収容数を超えています。", error.getMessage());
    }

    //

    @Test
    @DisplayName("動物体重の境界値分割")
    public void case17() throws ErrorException {

        var cage = new Cage();
        cage.setLimitWeight(new Weight(500));
        cage.setLimitSize(new Size(99));

        var a1 = new Animal();
        a1.setWeight(new AnimalWeight(9));
        cage.getAnimals().add(a1);
        var a2 = new Animal();
        a2.setWeight(new AnimalWeight(10));
        cage.getAnimals().add(a2);
        var a3 = new Animal();
        a3.setWeight(new AnimalWeight(11));
        cage.getAnimals().add(a3);
        var a4 = new Animal();
        a4.setWeight(new AnimalWeight(49));
        cage.getAnimals().add(a4);
        var a5 = new Animal();
        a5.setWeight(new AnimalWeight(50));
        cage.getAnimals().add(a5);
        var a6 = new Animal();
        a6.setWeight(new AnimalWeight(51));
        cage.getAnimals().add(a6);
        var a7 = new Animal();
        a7.setWeight(new AnimalWeight(99));
        cage.getAnimals().add(a7);
        var a8 = new Animal();
        a8.setWeight(new AnimalWeight(100));
        cage.getAnimals().add(a8);
        var a9 = new Animal();
        a9.setWeight(new AnimalWeight(101));
        cage.getAnimals().add(a9);

        var animal = new Animal();
        animal.setWeight(new AnimalWeight(8));

        var testSuite = new AddHouse(cage, animal);
        assertEquals(10, cage.getAnimals().size());
        assertEquals("制限重量を20Kg超えました。", testSuite.getMessage());
    }
}
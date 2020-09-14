package StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class CatDogQueue {
    public static void main(String[] args) {
        DogCatQueue queue = new DogCatQueue();
        for (int i = 0; i < 3; i++) {
            queue.add(new Dog());
        }
        for (int i = 0; i < 5; i++) {
            queue.add(new Cat());
        }

        System.out.println("插入完成！");
        if (queue.isEmpty()) {
            return;
        }
        if (queue.isCatQueueEmpty()) {
            for (int i = 0; i < 2; i++) {
                System.out.println("第" + i + "个" + queue.pollCat().getPetType());
            }
        }
        if (queue.isDogEmpty()) {
            System.out.println("第" + 0 + "个" + queue.pollDog().getPetType());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + i + "个" + queue.pollAll().getPetType());
        }
    }
}

class Pet {
    private final String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}

class PetEnterQueue {
    private final Pet pet;
    private final long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }
}

class DogCatQueue {
    private final Queue<PetEnterQueue> dogQue;
    private final Queue<PetEnterQueue> catQue;
    private long count;

    public DogCatQueue() {
        this.dogQue = new LinkedList<>();
        this.catQue = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            this.dogQue.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getPetType().equals("cat")) {
            this.catQue.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("err, not dog or cat!");
        }
    }

    public Pet pollAll() {
        if (!this.dogQue.isEmpty() && !this.catQue.isEmpty()) {
            if (this.dogQue.peek().getCount() < this.catQue.peek().getCount()) {
                return this.dogQue.poll().getPet();
            } else {
                return this.catQue.poll().getPet();
            }
        } else if (!this.dogQue.isEmpty()) {
            return this.dogQue.poll().getPet();
        } else if (!this.catQue.isEmpty()) {
            return this.catQue.poll().getPet();
        } else {
            throw new RuntimeException("err, queue is empty");
        }
    }

    public Dog pollDog() {
        if (!this.dogQue.isEmpty()) {
            return (Dog) this.dogQue.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty");
        }
    }

    public Cat pollCat() {
        if (!this.catQue.isEmpty()) {
            return (Cat) this.catQue.poll().getPet();
        } else {
            throw new RuntimeException("Cat queue is empty");
        }
    }

    public boolean isEmpty() {
        return this.dogQue.isEmpty() && this.catQue.isEmpty();
    }

    public boolean isDogEmpty() {
        return this.dogQue.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return this.catQue.isEmpty();
    }
}


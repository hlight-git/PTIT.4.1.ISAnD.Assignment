package dao.type;

import java.util.List;

public interface Readable<T>{
    public List<T> getAll();
    public List<T> getByNameContains(String keyword);
    public T getById(int id);
}

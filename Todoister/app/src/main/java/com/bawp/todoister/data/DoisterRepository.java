package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class DoisterRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTask;

    public DoisterRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTask = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTask(){
        return allTask;
    }

    public void insert(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->taskDao.insertTask(task));
    }

    public LiveData<Task> get(Long id){return taskDao.get(id);}

    public void update(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->taskDao.update(task));
    }

    public void delete(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->taskDao.delete(task));
    }
}

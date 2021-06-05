package com.wonddak.study1.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDAO {
    //최초 입력시 -1번
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTODO(toDoData: ToDoData)

    //클릭시 해당 id의 check 항목을 변경 - 2번
    @Query("UPDATE ToDoData SET 'check'= 1  WHERE id=:id")
    fun updateCheckTrueById(id: Int)

    //클릭시 해당 id의 check 항목을 변경 - 2번
    @Query("UPDATE ToDoData SET 'check'= 0  WHERE id=:id")
    fun updateCheckFalseById(id: Int)

    //누른 항목 삭제 -3번
    @Delete
    fun DeleteById(vararg toDoData: ToDoData)

    // 리스트 가져오기 -4번
    @Query("SELECT * FROM ToDoData")
    fun getToDoData(): LiveData<List<ToDoData>>

}
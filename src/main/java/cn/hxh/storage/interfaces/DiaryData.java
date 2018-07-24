package cn.hxh.storage.interfaces;

import cn.hxh.object.Diary;

public interface DiaryData {

    boolean delete(Diary.Key date);

    boolean create(Diary diary);

    boolean update(Diary diary);

    Diary query(Diary.Key date);
}

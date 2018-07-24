package cn.hxh.controller.diary;

import cn.hxh.common.log.Log;
import cn.hxh.object.Diary;
import cn.hxh.storage.interfaces.DiaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Controller
public class DiaryService {
    @Autowired
    DiaryData diaryData;
    @Autowired
    Log myLog;

    @RequestMapping(value = "/diary", method = RequestMethod.GET)
    public String dairy() {
        return "calendar";
    }

    @ResponseBody
    @GetMapping(value = "/diary/{year}/{month}/{date}")
    public Diary get(@PathVariable("year") @Max(2025) @Min(2018) int year,
                     @PathVariable("month") @Max(12) @Min(1) int month,
                     @PathVariable("date") @Max(31) @Min(1) int date) {
        Diary diary = diaryData.query(new Diary.Key(year, month, date));
        if (diary == null) {
            myLog.record(String.format("Query diary unsuccessfully-> %s-%s-%s", year, month, date));
            return Diary.error();
        } else {
            myLog.record(String.format("Query diary successfully-> %s-%s-%s", year, month, date));
            return diary;
        }
    }

    @ResponseBody
    @DeleteMapping(value = "/diary/{year}/{month}/{date}")
    public Code delete(@PathVariable("year") @Max(2025) @Min(2018) int year,
                       @PathVariable("month") @Max(12) @Min(1) int month,
                       @PathVariable("date") @Max(31) @Min(1) int date) {
        Code re = new Code();
        if (diaryData.delete(new Diary.Key(year, month, date))) {
            re.setCode(0);
            myLog.record(String.format("Delete diary successfully-> %s-%s-%s", year, month, date));
        } else {
            re.setCode(1);
            myLog.record(String.format("Delete diary unsuccessfully-> %s-%s-%s", year, month, date));
        }
        return re;
    }

    @ResponseBody
    @PostMapping(value = "/diary")
    public Code create(@RequestBody @Valid Diary diary) {
        Code re = new Code();
        if (diaryData.create(diary)) {
            re.setCode(0);
            myLog.record(String.format("Create diary successfully-> %s", diary.getDate().toString()));
        } else {
            re.setCode(1);
            myLog.record(String.format("Create diary unsuccessfully-> %s", diary.getDate().toString()));
        }
        return re;
    }


    @ResponseBody
    @PutMapping(value = "/diary")
    public Code update(@RequestBody @Valid Diary diary) {
        Code re = new Code();
        if (diaryData.update(diary)) {
            re.setCode(0);
            myLog.record(String.format("Update diary successfully-> %s", diary.getDate().toString()));
        } else {
            re.setCode(1);
            myLog.record(String.format("Update diary unsuccessfully-> %s", diary.getDate().toString()));
        }
        return re;
    }
}

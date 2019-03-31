package com.example.dress.util.ShowLetter;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class ShowLetterGroup extends LitePalSupport implements Serializable {
    private int groupId;
    private List<TempShowLetter> showLetters;

    public ShowLetterGroup(int groupId, List<TempShowLetter> showLetters) {
        this.groupId = groupId;
        this.showLetters = showLetters;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<TempShowLetter> getShowLetters() {
        return showLetters;
    }

    public void setShowLetters(List<TempShowLetter> showLetters) {
        this.showLetters = showLetters;
    }
}

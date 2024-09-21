package org.zerock.project.domain;

import lombok.Data;

@Data
public class Criteria {
    private int pageNum = 1;
    private int amount = 10;

    public void setPageNum(int pageNum) {
        if(pageNum <= 0){
            this.pageNum = pageNum = 1;
            return;
        }
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        if(amount <= 0 || amount > 100){
            this.pageNum = pageNum = 10;
            return;
        }
        this.amount = amount;
    }

    public int getSkip() {
        return(this.pageNum - 1) * this.amount;
    }
}

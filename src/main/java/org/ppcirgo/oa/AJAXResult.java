package org.ppcirgo.oa;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AJAXResult {
    /**
     * 返回给前端
     * code:4009 desc:error
     * code:7006 desc:success
     * code:5003 desc:logicerr
     */
    private int code=7006;
    private String desc="success";
    private Object msg;

    public AJAXResult(Object msg){
        this.msg = msg;
    }
    public AJAXResult(int code,Object msg){
        this.msg =msg;
        this.code=code;
        switch (code){
            case 4009:
                this.desc="error";
                break;
            case 7006:
                this.desc="success";
                break;
            default:
                this.desc="logicerr";
                this.code=5003;
                break;
        }
    }
}

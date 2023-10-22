package com.zyfproject.reggie.dto;


import com.zyfproject.reggie.entity.Setmeal;
import com.zyfproject.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

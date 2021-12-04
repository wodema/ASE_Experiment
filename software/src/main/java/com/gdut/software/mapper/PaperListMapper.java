package com.gdut.software.mapper;

/**
 * @author 22427(king0liam)
 * @version 1.0
 * @ClassName: PaperListMapper
 * @Description
 * @Date: 2021/12/2 21:14
 * @since version-0.0
 */

import com.gdut.software.entity.PaperList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperListMapper {
//    List<PaperList>getPaperList(int id);
    List<PaperList> getPaperList();

    int addPaperList(PaperList paperList);
}

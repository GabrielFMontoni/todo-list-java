package br.com.gabrielmontoni.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class Utils {
    
    public static void copyNonNullProperties(Object objRequisicao, Object objBancoDados){
        BeanUtils.copyProperties(objRequisicao, objBancoDados, getNullPropertyNames(objRequisicao));

    }


    public static String[] getNullPropertyNames(Object objRequisicao){
        final BeanWrapper src = new BeanWrapperImpl(objRequisicao);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
                
            }
        }

        String[] result = new String[emptyNames.size()];    
        return emptyNames.toArray(result);

    }
}

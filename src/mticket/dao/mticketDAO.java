/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mticket.dao;

import java.util.List;

/**
 *
 * @author HuyTinh
 * @param <E>
 * @param <K>
 */
public interface mticketDAO<E, K> {
    
//Thêm mới một bản ghi với dữ liệu là entity E
default void insert(E entity) {
    };
//Cập nhật một bản ghi với dữ liệu là entity E
default void update(E entity) {
    };
//Xóa một bản ghi với mã K
default void delete(E entity) {
    };
//Truy vấn một bản ghi với mã là K
List<E> selectAll();
//Truy vấn tất cả các bản ghi
default E selectById(K id) {
        return null;
    };
//Truy vấn các bản ghi tùy vào sql và args
List<E> selectBySql(String sql, Object... args);

}

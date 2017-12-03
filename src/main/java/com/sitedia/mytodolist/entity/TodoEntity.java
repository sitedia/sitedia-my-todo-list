package com.sitedia.mytodolist.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * To-do entity
 * @author sitedia
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "todos")
@NamedNativeQueries({
        @NamedNativeQuery(name = "todo.getCategories", query = "SELECT c.* FROM categories c, todo_categories tc WHERE c.id = tc.category_id and tc.todo_id = :todoId", resultClass = CategoryEntity.class),
        @NamedNativeQuery(name = "todo.getIdsByCategory", query = "SELECT todo_id FROM todo_categories WHERE category_id = :categoryId") })
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    private String description;

    @DateTimeFormat
    private Date creationDate;

}

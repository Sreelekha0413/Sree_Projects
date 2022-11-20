/*
 * package com.maersk.container.bookings.entity;
 * 
 * import java.time.LocalDateTime;
 * 
 * import org.springframework.data.cassandra.core.mapping.Column; import
 * org.springframework.data.cassandra.core.mapping.PrimaryKey; import
 * org.springframework.data.cassandra.core.mapping.Table;
 * 
 * @Table("bookings") public class Bookings {
 * 
 * @PrimaryKey private Long id;
 * 
 * @Column("container_size") private int containerSize;
 * 
 * @Column("container_type") private String containerType;
 * 
 * @Column("origin") private String origin;
 * 
 * @Column("destination") private String destination;
 * 
 * @Column("quantity") private Integer quantity;
 * 
 * @Column("timestamp") private LocalDateTime timestamp;
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public int getContainerSize() { return containerSize; }
 * 
 * public void setContainerSize(int containerSize) { this.containerSize =
 * containerSize; }
 * 
 * public String getContainerType() { return containerType; }
 * 
 * public void setContainerType(String containerType) { this.containerType =
 * containerType; }
 * 
 * public String getOrigin() { return origin; }
 * 
 * public void setOrigin(String origin) { this.origin = origin; }
 * 
 * public String getDestination() { return destination; }
 * 
 * public void setDestination(String destination) { this.destination =
 * destination; }
 * 
 * public Integer getQuantity() { return quantity; }
 * 
 * public void setQuantity(Integer quantity) { this.quantity = quantity; }
 * 
 * public LocalDateTime getTimestamp() { return timestamp; }
 * 
 * public void setTimestamp(LocalDateTime timestamp) { this.timestamp =
 * timestamp; }
 * 
 * public Bookings() { }
 * 
 * public Bookings(Long id, int containerSize, String containerType, String
 * origin, String destination, Integer quantity, LocalDateTime timestamp) {
 * super(); this.id = id; this.containerSize = containerSize; this.containerType
 * = containerType; this.origin = origin; this.destination = destination;
 * this.quantity = quantity; this.timestamp = timestamp; }
 * 
 * @Override public String toString() { return "Bookings [id=" + id +
 * ", containerSize=" + containerSize + ", containerType=" + containerType +
 * ", origin=" + origin + ", destination=" + destination + ", quantity=" +
 * quantity + ", timestamp=" + timestamp + "]"; }
 * 
 * }
 */
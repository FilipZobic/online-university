package rs.ac.singidunum.org.apiusers.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Administrator extends User{
}

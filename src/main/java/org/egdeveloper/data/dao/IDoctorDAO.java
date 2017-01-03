package org.egdeveloper.data.dao;

import org.egdeveloper.data.dao.impl.DoctorDAO;
import org.egdeveloper.data.model.Doctor;

import java.util.List;


/** Common interface of DAO for manipulating with doctor's information data
 * @author Yarnykh Roman egdeveloper@mail.ru
 * @version 1.0
 * @since 05.01.2016 (5th January 2016)
 * @see DoctorDAO
 */
public interface IDoctorDAO {

    /**
     * Add new doctor's record in database
     * @param doctor new doctor's record
     */
    void saveDoctor(Doctor doctor);

    /**
     * Update existing doctor's record in database
     * @param doctor existing doctor's entry
     */
    void updateDoctor(Doctor doctor);

    /**
     * Get all doctor's records
     * @return list of existing doctor's records from local database
     */
    List<Doctor> findDoctors();

    /**
     * Get existing doctor's record by it's login and password
     * @param login login
     * @return existing doctor's record
     */
    Doctor authDoctor(String login);

    /**
     * Get doctor's record from local database
     * @param id id of existing doctor's record
     * @return existing doctor's record
     */
    Doctor findDoctorById(Integer id);

    /**
     * Edit doctor's record in database
     * @param doctor edited doctor's record
     */
    void editDoctorInfo(Doctor doctor);

    /**
     * Remove existing doctor's record
     * @param id id of existing doctor's record
     */
    void deleteDoctor(Integer id);
}

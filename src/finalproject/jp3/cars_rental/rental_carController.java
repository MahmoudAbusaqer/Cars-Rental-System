/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.jp3.cars_rental;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mahmoud_Abusaqer
 */
public class rental_carController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Pane login_Pane;
    @FXML
    private Pane chooes_date_pane;
    @FXML
    private DatePicker pickUp_date;
    @FXML
    private DatePicker return_date;
    @FXML
    private Button search_Cars_Button;
    @FXML
    private Pane available_cars_pane;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button logIn;
    @FXML
    private Button user;
    @FXML
    private TableView<AvailableCars> available_Cars_Table;
    @FXML
    private TableColumn<AvailableCars, Integer> tc_Number;
    @FXML
    private TableColumn<AvailableCars, Integer> tc_Car_Id;
    @FXML
    private Button Go_back_to_change_the_Date_Button;
    @FXML
    private Button Go_to_Car_Informaion_Button;
    @FXML
    private Pane car_information_pane;
    @FXML
    private Label car_id;
    @FXML
    private Label model;
    @FXML
    private Label brand;
    @FXML
    private Label num_of_doors;
    @FXML
    private Label num_of_seats;
    @FXML
    private Label speed;
    @FXML
    private Label color;
    @FXML
    private Label price_per_day;
    @FXML
    private Button Go_to_Booking_Button;
    @FXML
    private Button Go_back_to_change_the_car_Button;
    @FXML
    private Pane booking_pane;
    @FXML
    private TextField customer_Id;
    @FXML
    private TextField first_Name;
    @FXML
    private TextField last_Name;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton femal;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField total_Rent_Days;
    @FXML
    private Label total_Price;
    @FXML
    private Button go_Back_Car_Information_Button;
    @FXML
    private Button Booking_Button;
    @FXML
    private TableView<Booking> view_Bookings_Table;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_Id;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_customerId;
    @FXML
    private TableColumn<Booking, Integer> tc_VB_carId;
    @FXML
    private TableColumn<Booking, Date> tc_VB_pickupDate;
    @FXML
    private TableColumn<Booking, Date> tc_VB_returnDate;
    @FXML
    private TableColumn<Booking, Double> tc_VB_totalDaysRent;
    @FXML
    private TableColumn<Booking, Double> tc_VB_totalPrice;
    @FXML
    private Pane view_Bookings_Pane;
    @FXML
    private ImageView background_Image;
    @FXML
    private HBox login_hbox;
    @FXML
    private HBox chooes_Date_hbox;
    @FXML
    private HBox available_Cars_hbox;
    @FXML
    private HBox car_Information_hbox;
    @FXML
    private HBox booking_hbox;
    @FXML
    private HBox view_booking_hbox;
    @FXML
    private TextField customer_id_update;
    @FXML
    private TextField car_id_update;
    @FXML
    private DatePicker picked_up_date_update;
    @FXML
    private DatePicker return_date_update;
    @FXML
    private TextField total_days_rent_update;
    @FXML
    private Button update_Button;
    @FXML
    private Button delete_Button;
    @FXML
    private Label id_update;
    @FXML
    private Pane add_Car_Pane;
    @FXML
    private Button signOut_Button;
    @FXML
    private HBox add_Car_hbox;
    @FXML
    private TextField id_addCar;
    @FXML
    private TextField model_addCar;
    @FXML
    private TextField brand_addCar;
    @FXML
    private TextField color_addCar;
    @FXML
    private TextField number_of_doors_addCar;
    @FXML
    private TextField number_of_seats_addCar;
    @FXML
    private Label total_Price1;
    @FXML
    private TextField price_per_day_addCar;
    @FXML
    private TextField speed_addCar;
    @FXML
    private Button add_newCar_Button;

    Statement statement;
    List<Integer> availableCarsList = new ArrayList<>();
    Boolean isAdmin = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cars_rental?serverTimezone=UTC",
                            "root", "");
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        //Available Cars
        tc_Number.setCellValueFactory(new PropertyValueFactory("id"));
        tc_Car_Id.setCellValueFactory(new PropertyValueFactory("carId"));
        //View Bookings
        tc_VB_Id.setCellValueFactory(new PropertyValueFactory("id"));
        tc_VB_customerId.setCellValueFactory(new PropertyValueFactory("customerId"));
        tc_VB_carId.setCellValueFactory(new PropertyValueFactory("carId"));
        tc_VB_pickupDate.setCellValueFactory(new PropertyValueFactory("pickupDate"));
        tc_VB_returnDate.setCellValueFactory(new PropertyValueFactory("returnDate"));
        tc_VB_totalDaysRent.setCellValueFactory(new PropertyValueFactory("totalDaysRent"));
        tc_VB_totalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        login_Pane.toFront();
        login_Pane.setVisible(true);
        background_Image.setVisible(false);
        chooes_date_pane.setVisible(false);
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
        view_Bookings_Pane.setVisible(false);
        add_Car_Pane.setVisible(false);
        login_hbox.setStyle("-fx-background-color: #c4dff6;");
        view_Bookings_Table.getSelectionModel().selectedItemProperty().addListener(listener -> selectBooking());
        try {
            if (rootPane.isVisible()) {
                showAvailableCar();
                pickUp_date.setValue(LocalDate.now());
            }
        } catch (SQLException ex) {
            Logger.getLogger(rental_carController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logIn_Button_Handle(ActionEvent event) throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from employee");
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getNString("name"));
            employee.setEmployeePassword(resultSet.getString("employee_password"));
            employees.add(employee);
            String employeePassword = md5Java(password.getText());
            if (Integer.parseInt(userName.getText()) == (employee.getId()) && employeePassword.equals(employee.getEmployeePassword())) {
                login_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: #c4dff6;");
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                view_Bookings_Pane.setVisible(true);
                booking_pane.setVisible(false);
                chooes_date_pane.setVisible(false);
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                view_Bookings_Pane.toFront();
                add_Car_Pane.setVisible(false);
                showBookings();
                isAdmin = true;
                break;
            } else if (userName.getText().equals("") || password.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You can't log in you should fill all the fields before");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("ID or Password is wrong");
                alert.showAndWait();
            }
        }
        resultSet.close();
    }

    @FXML
    private void search_Cars_Button_Handle(ActionEvent event) throws SQLException {
        checkBeforeGoToAVCar();
        chooes_Date_hbox.setStyle("-fx-background-color: none;");
        available_Cars_hbox.setStyle("-fx-background-color: #c4dff6;");
    }

    @FXML
    private void Go_back_to_change_the_Date_Button_Handle(ActionEvent event) {
        chooes_Date_hbox.setStyle("-fx-background-color: #c4dff6;");
        available_Cars_hbox.setStyle("-fx-background-color: none;");
        chooes_date_pane.setVisible(true);
        chooes_date_pane.toFront();
        available_cars_pane.setVisible(false);
    }

    @FXML
    private void Go_to_Car_Informaion_Button_Handle(ActionEvent event) throws SQLException {
        selectAvailableCars();
        car_Information_hbox.setStyle("-fx-background-color: #c4dff6;");
        available_Cars_hbox.setStyle("-fx-background-color: none;");
    }

    @FXML
    private void Go_back_to_change_the_Car_Button_Handle(ActionEvent event) {
        available_Cars_hbox.setStyle("-fx-background-color: #c4dff6;");
        car_Information_hbox.setStyle("-fx-background-color: none;");
        available_cars_pane.setVisible(true);
        chooes_date_pane.setVisible(false);
        available_cars_pane.toFront();
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
    }

    @FXML
    private void Go_to_Booking_Button_Handle(ActionEvent event) throws SQLException {
        ToggleGroup toggleGroup = new ToggleGroup();
        male.setToggleGroup(toggleGroup);
        femal.setToggleGroup(toggleGroup);
        booking_hbox.setStyle("-fx-background-color: #c4dff6;");
        car_Information_hbox.setStyle("-fx-background-color: none;");
        booking_pane.setVisible(true);
        chooes_date_pane.setVisible(false);
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.toFront();
        Integer totalRentDays = Integer.parseInt(total_Rent_Days.getText());
        Double totalPrice = totalRentDays * Double.parseDouble(price_per_day.getText());
        total_Price.setText(String.valueOf(totalPrice));

    }

    @FXML
    private void go_Back_Car_Information_Button_Handle(ActionEvent event) {
        booking_hbox.setStyle("-fx-background-color: none;");
        car_Information_hbox.setStyle("-fx-background-color: #c4dff6;");
        car_information_pane.setVisible(true);
        chooes_date_pane.setVisible(false);
        available_cars_pane.setVisible(false);
        car_information_pane.toFront();
        booking_pane.setVisible(false);
    }

    @FXML
    private void booking_Button_Handle(ActionEvent event) throws SQLException {
        booking_hbox.setStyle("-fx-background-color: none;");
        addCustomer_Booking();
    }

    private void showAvailableCar() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from available_cars");
        available_Cars_Table.getItems().clear();
        int number = 1;
        while (resultSet.next()) {
            AvailableCars availableCars = new AvailableCars();
            availableCars.setId(number);
            availableCars.setCarId(resultSet.getInt("car_id"));
            availableCarsList.add(availableCars.getCarId());
            available_Cars_Table.getItems().add(availableCars);
            number++;
        }
        resultSet.close();
    }

    private void addCustomer_Booking() throws SQLException {
        if (customer_Id.getText().equals("") || first_Name.getText().equals("") || last_Name.getText().equals("")
                || (male.getText().equals("") && femal.getText().equals("")) || address.getText().equals("")
                || phone.getText().equals("") || total_Rent_Days.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You can't book you should fill all the fields");
            alert.showAndWait();
        } else {
            //customer 
            Integer customerId = Integer.parseInt(customer_Id.getText());
            String firstName = first_Name.getText();
            String lastName = last_Name.getText();
            String customerAddress = address.getText();
            String customerPhone = phone.getText();
            String gender = null;
            if (male.getText() != null) {
                gender = male.getText();
            } else if (femal.getText() != null) {
                gender = femal.getText();
            }
            String addCustomerSql = "insert into customer values(" + customerId + ", '" + firstName + "', '" + lastName
                    + "', '" + customerAddress + "', '" + customerPhone + "', '" + gender + "')";
            int addCustomer = this.statement.executeUpdate(addCustomerSql);

            //booking 
            Integer carId = Integer.parseInt(car_id.getText());

            Integer totalRentDays = Integer.parseInt(total_Rent_Days.getText());
            Double totalPrice = Double.parseDouble(total_Price.getText());
            String addBookingSql = "insert into booking (customer_id, car_id, pickup_date, return_date, total_days_rent, total_price)"
                    + " values(" + customerId + ", " + carId + ", '" + pickUp_date.getValue()
                    + "', '" + return_date.getValue() + "', " + totalRentDays + ", " + totalPrice + ")";
            int addBooking = this.statement.executeUpdate(addBookingSql);

            //updateing car available to no
            String updateCar = "UPDATE car SET available = 'no' WHERE id = " + carId;
            this.statement.executeUpdate(updateCar);
            //add car to booked_cars
            ResultSet resultSet = this.statement.executeQuery("select * from booking where car_id =" + car_id.getText() + "");
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                String addToBooked_cars = "insert into booked_cars (booking_id, car_id, customer_id, end_date)"
                        + " values(" + booking.getId() + ", " + carId + ", " + customerId + ", '" + return_date.getValue() + "')";
                this.statement.executeUpdate(addToBooked_cars);
                break;
            }
            resultSet.close();
            //delete car from available_cars
            String deletefromAvailable_cars = "DELETE FROM available_cars WHERE car_id=" + carId;
            this.statement.executeUpdate(deletefromAvailable_cars);
            if (addCustomer == 1 && addBooking == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Car Booked");
                alert.setContentText("You can now take the car 😄");
                alert.showAndWait();
                clearFields();
                login_Pane.setVisible(true);
                login_Pane.toFront();
                booking_pane.setVisible(false);
            }
        }
    }

    private void selectAvailableCars() throws SQLException {
        AvailableCars availableCars = available_Cars_Table.getSelectionModel().getSelectedItem();
        if (availableCars != null) {
            ResultSet resultSet = this.statement.executeQuery("select * from Car where id =" + availableCars.getCarId() + "");
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car_id.setText(String.valueOf(car.getId()));
                car.setModel(resultSet.getString("model"));
                model.setText(car.getModel());
                car.setBrand(resultSet.getString("brand"));
                brand.setText(car.getBrand());
                car.setNumDoors(resultSet.getInt("num_doors"));
                num_of_doors.setText(String.valueOf(car.getNumDoors()));
                car.setNumSeats(resultSet.getInt("num_seats"));
                num_of_seats.setText(String.valueOf(car.getNumSeats()));
                car.setColor(resultSet.getString("color"));
                color.setText(car.getColor());
                car.setSpeed(resultSet.getInt("speed"));
                speed.setText(String.valueOf(car.getSpeed()));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                price_per_day.setText(String.valueOf(car.getPricePerDay()));
            }
            resultSet.close();
            car_information_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.setVisible(false);
            car_information_pane.toFront();
            booking_pane.setVisible(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You should select a car to view it's information");
            alert.showAndWait();
            available_cars_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.toFront();
            car_information_pane.setVisible(false);
            booking_pane.setVisible(false);
        }
    }

    private void checkBeforeGoToAVCar() throws SQLException {
        if (pickUp_date.getValue() == null || return_date.getValue() == null || total_Rent_Days.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You should choose the date or fill the field to go next page");
            alert.showAndWait();
        } else {
            available_cars_pane.setVisible(true);
            chooes_date_pane.setVisible(false);
            available_cars_pane.toFront();
            car_information_pane.setVisible(false);
            booking_pane.setVisible(false);
            available_Cars_Table.getItems().clear();
            showAvailableCar();
        }
    }

    private void showBookings() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from booking");
        view_Bookings_Table.getItems().clear();
        List<Booking> bookings = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("id"));
            booking.setCustomerId(resultSet.getInt("customer_id"));
            booking.setCarId(resultSet.getInt("car_id"));
            booking.setPickupDate(resultSet.getDate("pickup_date"));
            booking.setReturnDate(resultSet.getDate("return_date"));
            booking.setTotalDaysRent(resultSet.getDouble("total_days_rent"));
            booking.setTotalPrice(resultSet.getDouble("total_price"));
            bookings.add(booking);
            view_Bookings_Table.getItems().setAll(bookings);
        }
        resultSet.close();
    }

    @FXML
    private void hBox_Handle(MouseEvent event) throws SQLException {
        if (isAdmin) {
            if (event.getSource() == login_hbox) {
                login_Pane.toFront();
                login_Pane.setVisible(true);
                background_Image.setVisible(false);
                chooes_date_pane.setVisible(false);
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                booking_pane.setVisible(false);
                view_Bookings_Pane.setVisible(false);
                add_Car_Pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: #c4dff6;");
                chooes_Date_hbox.setStyle("-fx-background-color: none;");
                available_Cars_hbox.setStyle("-fx-background-color: none;");
                car_Information_hbox.setStyle("-fx-background-color: none;");
                booking_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: none;");
                add_Car_hbox.setStyle("-fx-background-color: none;");
            } else if (event.getSource() == chooes_Date_hbox) {
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                chooes_date_pane.setVisible(true);
                chooes_date_pane.toFront();
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                booking_pane.setVisible(false);
                view_Bookings_Pane.setVisible(false);
                add_Car_Pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: none;");
                chooes_Date_hbox.setStyle("-fx-background-color: #c4dff6;");
                available_Cars_hbox.setStyle("-fx-background-color: none;");
                car_Information_hbox.setStyle("-fx-background-color: none;");
                booking_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: none;");
                add_Car_hbox.setStyle("-fx-background-color: none;");
            } else if (event.getSource() == available_Cars_hbox) {
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                available_cars_pane.setVisible(true);
                chooes_date_pane.setVisible(false);
                available_cars_pane.toFront();
                car_information_pane.setVisible(false);
                booking_pane.setVisible(false);
                view_Bookings_Pane.setVisible(false);
                add_Car_Pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: none;");
                chooes_Date_hbox.setStyle("-fx-background-color: none;");
                available_Cars_hbox.setStyle("-fx-background-color: #c4dff6;");
                car_Information_hbox.setStyle("-fx-background-color: none;");
                booking_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: none;");
                add_Car_hbox.setStyle("-fx-background-color: none;");
            } else if (event.getSource() == car_Information_hbox) {
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                car_information_pane.setVisible(true);
                chooes_date_pane.setVisible(false);
                available_cars_pane.setVisible(false);
                car_information_pane.toFront();
                booking_pane.setVisible(false);
                view_Bookings_Pane.setVisible(false);
                add_Car_Pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: none;");
                chooes_Date_hbox.setStyle("-fx-background-color: none;");
                available_Cars_hbox.setStyle("-fx-background-color: none;");
                car_Information_hbox.setStyle("-fx-background-color: #c4dff6;");
                booking_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: none;");
                add_Car_hbox.setStyle("-fx-background-color: none;");
            } else if (event.getSource() == booking_hbox) {
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                booking_pane.setVisible(true);
                chooes_date_pane.setVisible(false);
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                view_Bookings_Pane.setVisible(false);
                booking_pane.toFront();
                add_Car_Pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: none;");
                chooes_Date_hbox.setStyle("-fx-background-color: none;");
                available_Cars_hbox.setStyle("-fx-background-color: none;");
                car_Information_hbox.setStyle("-fx-background-color: none;");
                booking_hbox.setStyle("-fx-background-color: #c4dff6;");
                view_booking_hbox.setStyle("-fx-background-color: none;");
                add_Car_hbox.setStyle("-fx-background-color: none;");
            } else if (event.getSource() == view_booking_hbox) {
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                view_Bookings_Pane.setVisible(true);
                booking_pane.setVisible(false);
                chooes_date_pane.setVisible(false);
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                view_Bookings_Pane.toFront();
                add_Car_Pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: none;");
                chooes_Date_hbox.setStyle("-fx-background-color: none;");
                available_Cars_hbox.setStyle("-fx-background-color: none;");
                car_Information_hbox.setStyle("-fx-background-color: none;");
                booking_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: #c4dff6;");
                add_Car_hbox.setStyle("-fx-background-color: none;");
                showBookings();
            } else if (event.getSource() == add_Car_hbox) {
                add_Car_Pane.setVisible(true);
                add_Car_Pane.toFront();
                background_Image.setVisible(true);
                login_Pane.setVisible(false);
                view_Bookings_Pane.setVisible(false);
                booking_pane.setVisible(false);
                chooes_date_pane.setVisible(false);
                available_cars_pane.setVisible(false);
                car_information_pane.setVisible(false);
                login_hbox.setStyle("-fx-background-color: none;");
                chooes_Date_hbox.setStyle("-fx-background-color: none;");
                available_Cars_hbox.setStyle("-fx-background-color: none;");
                car_Information_hbox.setStyle("-fx-background-color: none;");
                booking_hbox.setStyle("-fx-background-color: none;");
                view_booking_hbox.setStyle("-fx-background-color: none;");
                add_Car_hbox.setStyle("-fx-background-color: #c4dff6;");
            }
        }
    }

    public static String md5Java(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            ex.getStackTrace();
        }
        return digest;
    }

    @FXML
    private void update_Button_Handle(ActionEvent event) throws SQLException {
        Booking booking = view_Bookings_Table.getSelectionModel().getSelectedItem();
        if (booking != null) {
            ResultSet resultSet = this.statement.executeQuery("SELECT car.id as id, car_id, customer_id, price_per_day FROM car "
                    + "Left JOIN booked_cars ON car.id = booked_cars.car_id ");
            while (resultSet.next()) {
                BookedCars bookedCars = new BookedCars();
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                bookedCars.setCarId(resultSet.getInt("car_id"));
                bookedCars.setCustomerId(resultSet.getInt("customer_id"));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                Double totalRentDays = Double.parseDouble(total_days_rent_update.getText());
                Double totalPrice = totalRentDays * car.getPricePerDay();
                if (booking.getCarId().equals(Integer.parseInt(car_id_update.getText()))) {
                    if (!booking.getCustomerId().equals(Integer.parseInt(customer_id_update.getText()))) {
                        //update customer
                        String updateCustomer = "UPDATE customer SET id= " + customer_id_update.getText() + " WHERE id=" + booking.getCustomerId();
                        this.statement.executeUpdate(updateCustomer);
                    }
                    String updateCar = "UPDATE booking SET pickup_date='" + picked_up_date_update.getValue() + "', return_date='" + return_date_update.getValue() + "',"
                            + " total_days_rent=" + total_days_rent_update.getText() + ", total_price=" + totalPrice + " WHERE id = " + id_update.getText();
                    this.statement.executeUpdate(updateCar);
                    //updateing booked_car
                    String updateBooked_car = "UPDATE booked_cars SET end_date='" + return_date_update.getValue() + "' WHERE booking_id = " + id_update.getText();
                    this.statement.executeUpdate(updateBooked_car);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Update");
                    alert2.setContentText("The update was successfully done");
                    alert2.showAndWait();
                    showBookings();
                    clearFields();
                    break;

                } else if (bookedCars.getCarId().equals(Integer.parseInt(car_id_update.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("This car is already booked choose one that is available");
                    alert.showAndWait();
                    car_id_update.setText("");
                    break;

                } else if (car.getId().equals(Integer.parseInt(car_id_update.getText())) && !bookedCars.getCarId().equals(Integer.parseInt(car_id_update.getText()))
                        && !booking.getCarId().equals(Integer.parseInt(car_id_update.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Car Changed");
                    alert.setContentText("Are you sure that you want to change the car!");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        System.out.println("in");
                        if (!booking.getCustomerId().equals(Integer.parseInt(customer_id_update.getText()))) {
                            //update Customer
                            String updateCustomer = "UPDATE customer SET id= " + customer_id_update.getText() + " WHERE id=" + booking.getCustomerId();
                            this.statement.executeUpdate(updateCustomer);
                        }
                        //updateing car available to no
                        String updateCarAvailable = "UPDATE car SET available = 'no' WHERE id = " + car_id_update.getText();
                        this.statement.executeUpdate(updateCarAvailable);
                        //updateing car available to yes
                        String updateCarAvailable2 = "UPDATE car SET available = 'yes' WHERE id = " + booking.getCarId();
                        this.statement.executeUpdate(updateCarAvailable2);
                        //delete the old car from the booked_cars
                        String deletefromBooked_cars = "DELETE FROM booked_cars WHERE car_id=" + booking.getCarId();
                        this.statement.executeUpdate(deletefromBooked_cars);
                        //add new car to booked_cars
                        String addToBooked_cars = "insert into booked_cars (booking_id, car_id, customer_id, end_date)"
                                + " values(" + booking.getId() + ", " + car_id_update.getText() + ", " + customer_id_update.getText() + ", '" + return_date_update.getValue() + "')";
                        this.statement.executeUpdate(addToBooked_cars);
                        //delete car from available_cars
                        String deletefromAvailable_cars = "DELETE FROM available_cars WHERE car_id=" + car_id_update.getText();
                        this.statement.executeUpdate(deletefromAvailable_cars);
                        //add to available_cars
                        String addAvailable_cars = "insert into available_cars (car_id) values(" + booking.getCarId() + ")";
                        this.statement.executeUpdate(addAvailable_cars);
                        //update booking
                        String updateCar = "UPDATE booking SET car_id= " + car_id_update.getText() + ", pickup_date='" + picked_up_date_update.getValue() + "', return_date='" + return_date_update.getValue() + "',"
                                + " total_days_rent=" + total_days_rent_update.getText() + ", total_price=" + totalPrice + " WHERE id = " + id_update.getText();
                        this.statement.executeUpdate(updateCar);
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Update");
                        alert2.setContentText("The update was successfully done");
                        alert2.showAndWait();
                        showBookings();
                        showAvailableCar();
                        clearFields();
                        break;
                    }
                }
            }
            resultSet.close();
        }
    }

    @FXML
    private void delete_Button_Handle(ActionEvent event) throws SQLException {
        Booking booking = view_Bookings_Table.getSelectionModel().getSelectedItem();
        if (booking != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleteing Booking");
            alert.setContentText("Are you sure that you want to delete this booking?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //updateing car available to yes
                String updateCarAvailable = "UPDATE car SET available = 'yes' WHERE id = " + booking.getCarId();
                this.statement.executeUpdate(updateCarAvailable);
                //delete the old car from the booked_cars
                String deletefromBooked_cars = "DELETE FROM booked_cars WHERE car_id=" + booking.getCarId();
                this.statement.executeUpdate(deletefromBooked_cars);
                //add to available_cars
                String addAvailable_cars = "insert into available_cars (car_id) values(" + booking.getCarId() + ")";
                this.statement.executeUpdate(addAvailable_cars);
                //deleteing Customer
                String deleteCustomer = "DELETE FROM customer WHERE id=" + booking.getCustomerId();
                this.statement.executeUpdate(deleteCustomer);
                //deleteing booking
                String deleteBooking = "DELETE FROM booking WHERE id=" + booking.getId();
                this.statement.executeUpdate(deleteBooking);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete");
                alert2.setContentText("The delete was successfully done");
                alert2.showAndWait();
                showBookings();
                showAvailableCar();
                clearFields();
            }
        }
    }

    private void selectBooking() {
        Booking booking = view_Bookings_Table.getSelectionModel().getSelectedItem();
        if (booking != null) {
            id_update.setText(String.valueOf(booking.getId()));
            customer_id_update.setText(String.valueOf(booking.getCustomerId()));
            car_id_update.setText(String.valueOf(booking.getCarId()));
            picked_up_date_update.setValue(new java.sql.Date(booking.getPickupDate().getTime()).toLocalDate());
            return_date_update.setValue(new java.sql.Date(booking.getReturnDate().getTime()).toLocalDate());
            total_days_rent_update.setText(String.valueOf(booking.getTotalDaysRent()));
        }
    }

    private void clearFields() {
        pickUp_date.setValue(LocalDate.now());
        return_date.setValue(null);
        car_id.setText("");
        model.setText("");
        brand.setText("");
        num_of_doors.setText("");
        num_of_seats.setText("");
        speed.setText("");
        color.setText("");
        price_per_day.setText("");
        customer_Id.setText("");
        first_Name.setText("");
        last_Name.setText("");
        address.setText("");
        phone.setText("");
        total_Rent_Days.setText("");
        total_Price.setText("");
        id_update.setText("");
        customer_id_update.setText("");
        car_id_update.setText("");
        picked_up_date_update.setValue(null);
        return_date_update.setValue(null);
        total_days_rent_update.setText("");
        id_addCar.setText("");
        model_addCar.setText("");
        brand_addCar.setText("");
        color_addCar.setText("");
        number_of_doors_addCar.setText("");
        number_of_seats_addCar.setText("");
        speed_addCar.setText("");
        price_per_day_addCar.setText("");
    }

    @FXML
    private void signOut_Button_Handle(ActionEvent event) {
        isAdmin = false;
        login_Pane.toFront();
        login_Pane.setVisible(true);
        background_Image.setVisible(false);
        chooes_date_pane.setVisible(false);
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
        view_Bookings_Pane.setVisible(false);
        add_Car_Pane.setVisible(false);
        login_hbox.setStyle("-fx-background-color: #c4dff6;");
        view_booking_hbox.setStyle("-fx-background-color: none;");
        add_Car_hbox.setStyle("-fx-background-color: none;");
    }

    @FXML
    private void add_newCar_Button_Handle(ActionEvent event) throws SQLException {
        if (id_addCar.getText().equals("") || model_addCar.getText().equals("") || brand_addCar.getText().equals("")
                || color_addCar.getText().equals("") || number_of_doors_addCar.getText().equals("") || number_of_seats_addCar.getText().equals("")
                || speed_addCar.getText().equals("") || price_per_day_addCar.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You can't add new car you should fill all the fields");
            alert.showAndWait();
        } else {
            //add car
            String addNewCar = "insert into car values(" + id_addCar.getText() + ", '" + model_addCar.getText() + "', '" + brand_addCar.getText()
                    + "', " + number_of_doors_addCar.getText() + ", " + number_of_seats_addCar.getText() + ", '" + color_addCar.getText() + "'"
                    + ", " + speed_addCar.getText() + ", " + price_per_day_addCar.getText() + ", 'yes')";
            int addCar = this.statement.executeUpdate(addNewCar);
            //add car to available_cars
            String addAvailable_cars = "insert into available_cars (car_id) values(" + id_addCar.getText() + ")";
            this.statement.executeUpdate(addAvailable_cars);
            showAvailableCar();
            if (addCar == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Car Added");
                alert.setContentText("The car has been added to the system 😄");
                alert.showAndWait();
                clearFields();
            }
        }
    }

    @FXML
    private void user_Button_Handle(ActionEvent event) {
        login_hbox.setStyle("-fx-background-color: none;");
        chooes_Date_hbox.setStyle("-fx-background-color: #c4dff6;");
        background_Image.setVisible(true);
        login_Pane.setVisible(false);
        chooes_date_pane.setVisible(true);
        chooes_date_pane.toFront();
        available_cars_pane.setVisible(false);
        car_information_pane.setVisible(false);
        booking_pane.setVisible(false);
        view_Bookings_Pane.setVisible(false);
        add_Car_Pane.setVisible(false);
    }
}

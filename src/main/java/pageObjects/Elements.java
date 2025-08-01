package pageObjects;

public class Elements {

    public String signBtn = "NAME||login";
    public String loginBtn ="XPATH||//input[@class='login_button']";
    public String username = "ID||username";
    public String password="NAME||password";
    public String locationDrp="XPATH||//select[@name='location']";
    public String hotelsDrp="NAME||hotels";
    public String roomTypeDrp="NAME||room_type";
    public String numberOfRooms="NAME||room_nos";
    public String checkInDate="NAME||datepick_in";
    public String checkOutDate="NAME||datepick_out";
    public String adultPerRoomDrp="XPATH||//select[@name='adult_room']";
    public String childrenPeRoomDrp="XPATH||//select[@name='child_room']";
    public String searchBtn = "NAME||Submit";
    public String resetBtn = "NAME||reset";
    public String table="XPATH||//table[@class='login']/tbody";
    public String table2="XPATH||//*[@id=\"select_form\"]/table/tbody/tr[2]/td/table";
    public String continueBtn="NAME||continue";
    public String radioBtn="XPATH||//*[@id=\"select_form\"]/table/tbody/tr[2]/td/table//input[@type='radio']";
    public String firstName="XPATH||//input[@name='first_name']";
    public String lastName="XPATH||//input[@name='last_name']";
    public String BillingAddr="XPATH||//textarea[@name='address']";
    public String cardNo="XPATH||//input[@name='cc_num']";
    public String cardType="XPATH||//select[@name='cc_type']";
    public String ExperyDateMOnth="XPATH||//select[@name='cc_exp_month']";
    public String ExpiryDateYear="XPATH||//select[@name='cc_exp_year']";
    public String ccvNum="XPATH||//input[@name='cc_cvv']";
    public String bookNowBtn="XPATH||//input[@name='book_now']";

//    Assert field
    public String reservedHotel="XPATH||//input[@name='hotel_name_dis']";
    public String reservedNumOfRooms="XPATH||//input[@name='room_num_dis']";
    public String reservelocation="XPATH||//input[@name='location_dis']";





}

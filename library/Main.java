package Library-Manager;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Student u = new Student();
		System.out.println("Please enter your name: ");
		String name = sc.nextLine();
		System.out.println(u.setName(name));
		while (u.setName(name) != true) {
			
			name = sc.nextLine();
		}
		System.out.println("User name: " + u.toString());
	}
}

class User {
	private String ID;
	private String name;
	private String email;
	private String password;
	private String birthday;
	private String phoneNumber;
	private String address;
	private int gender;
	private int status;
	private String createdAt;
	private String updatedAt;

	public User(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, int gender){
		this.ID = ID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.status = 1;
		this.createdAt = getCurrentDatetime();
		this.updatedAt = getCurrentDatetime();
	}

	public User() {

	};

	private String getCurrentDatetime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if (isValidName(name)) {
			this.name = name;
			return true;
		} else {
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if (isValidEmail(email)) {
			this.email = email;
			return true;
		} else {
			return false;
		}
	}

	public String getPassword() {
		return password;
	}

	public boolean setPassword(String password) {
		if (isValidPassword(password)) {
			this.password = password;
			return true;
		} else {
			return false;
		}
	}

	public String getBirthday() {
		return birthday;
	}

	public boolean setBirthday(String birthday) {
		if (isValidBirthday(birthday)) {
			this.birthday = birthday;
			return true;
		} else {
			return false;
		}
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean setPhoneNumber(String phoneNumber) {
		if (isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
			return true;
		} else {
			return false;
		}
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		if (isValidAddress(address)) {
			this.address = address;
			return true;
		} else {
			return false;
		}
	}

	public int getGender() {
		return gender;
	}

	public boolean setGender(int gender) {
		this.gender = gender;
		return true;
	}

	public int getStatus() {
		return status;
	}

	public boolean setStatus(int status) {
		this.status = status;
		return true;
	}

	public String getID() {
		return ID;
	}

	private boolean isValidName(String name) {
		return name.length() >= 2 && name.length() <= 200;
	}

	private boolean isValidEmail(String email) {
		String regexEmail = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regexEmail);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean isValidPassword(String password) {
		String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n";
		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	private boolean isValidBirthday(String birthday) {
		String regexBirthday = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(regexBirthday);
		Matcher matcher = pattern.matcher(birthday);
		return matcher.matches();
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		String regexPhoneNumber = "^[0|81|84]+[1-9]{9,10}$";
		Pattern pattern = Pattern.compile(regexPhoneNumber);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	private boolean isValidAddress(String address) {
		return address.length() >= 3 && address.length() < 400;
	}

	private boolean repOk(String name, String email, String password, String birthday, String phoneNumber,
			String address) {
		return isValidName(name) && isValidEmail(email) && isValidPassword(password) && isValidBirthday(birthday)
				&& isValidPhoneNumber(phoneNumber) && isValidAddress(address);
	}

	@Override
	public String toString() {
		return "ID=" + ID + ", name=" + name + ", email=" + email + ", password=" + password + ", birthday="
				+ birthday + ", phoneNumber=" + phoneNumber + ", address=" + address + ", gender=" + gender;
	}

}

class Student extends User {
	private String course;
	private int year;
	private ArrayList<Student> studentList;

	public Student(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, int gender, String course, int year) throws Exception {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
		if (repOk(course, year)) {
			this.course = course;
			this.year = year;
			studentList = new ArrayList<Student>();
		}
	}

	public Student() {
		studentList = new ArrayList<Student>();
	}

	public String getCourse() {
		return course;
	}

	public boolean setCourse(String course) {
		if (isValidCourse(course)) {
			this.course = course;
			return true;
		} else {
			return false;
		}
	}

	public int getYear() {
		return year;
	}

	public boolean setYear(int year) {
		if (isValidYear(year)) {
			this.year = year;
			return true;
		} else {
			return false;
		}
	}

	private boolean isValidCourse(String course) {
		return course.length() >= 2 && course.length() <= 10;
	}

	private boolean isValidYear(int year) {
		return year >= 1 && year <= 8;
	}

	private boolean repOk(String course, int year) {
		return isValidCourse(course) && isValidYear(year);
	}
	
	public ArrayList<Student> getAllStudent(){
		return this.studentList;
	}

	@Override
	public String toString() {
		return super.toString() + ", course=" + course + ", year=" + year;
	}
	
}

class Admin extends User implements LibraryManagementTools, AdminTools {
	public Admin() {
		
	}

	public Admin(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, int gender) {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
	}

	@Override
	public int createLibrarian() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeLibrarian(int librarianID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLibrarian(int librarianID, Librarian librarian) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Librarian> getAllLibrarian() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createStudent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeStudent(int studentID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStudent(int studentID, Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int createRoom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeRoom(int roomID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRoom(int roomID, Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFacility(Facility facility) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFacility(int facilityID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateFacility(int facilityID, Facility facility) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBookDetail(int bookID, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBook(int bookID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Room> getAllRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Facility> getAllFacility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getAllBook() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

class Librarian extends User implements LibraryManagementTools{

	public Librarian() {
		
	}

	public Librarian(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, int gender) {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBookDetail(int bookID, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBook(int bookID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Room> getAllRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Facility> getAllFacility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Book> getAllBook() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

interface LibraryManagementTools{
	public boolean addBook(Book book);
	public boolean updateBookDetail(int bookID, Book book);
	public boolean removeBook(int bookID);
	public ArrayList<Student> getAllStudent();
	public ArrayList<Room> getAllRoom();
	public ArrayList<Facility> getAllFacility();
	public ArrayList<Book> getAllBook();
}

interface AdminTools {
	public int createLibrarian(); // return new Librarian ID
	public boolean removeLibrarian(int librarianID);
	public boolean updateLibrarian(int librarianID, Librarian librarian);
	public ArrayList<Librarian> getAllLibrarian();
	public int createStudent(); // return new Student ID
	public boolean removeStudent(int studentID);
	public boolean updateStudent(int studentID, Student student);
	public int createRoom(); // return new Room ID
	public boolean removeRoom(int roomID);
	public boolean updateRoom(int roomID, Room room);
	public boolean addFacility(Facility facility);
	public boolean removeFacility(int facilityID);
	public boolean updateFacility(int facilityID, Facility facility);
}

class Book {
	public Book() {
		
	}
}

class Room{
	public Room() {
		
	}
}

class Facility{
	public Facility() {
		
	}
}

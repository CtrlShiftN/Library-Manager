package library;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
	public static void main(String[] args) throws Exception {
		Admin ad = new Admin();
		ad.createLibrarian();
		ArrayList<Librarian> libList = ad.getAllLibrarian();
		libList.forEach((lib)->System.out.println(lib.toString()));
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
	
	public User(String ID) {
		this.ID = ID;
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
	
	private boolean promtName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter name: ");
		String name = sc.nextLine();
		while (this.setName(name) != true) {
			System.out.println("Invalid name format! Please re-enter name: ");
			name = sc.nextLine();
		}
		return true;
	}
	
	private boolean promtEmail() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter email: ");
		String email = sc.nextLine();
		while (this.setEmail(email) != true) {
			System.out.println("Invalid email format! Please re-enter email: ");
			email = sc.nextLine();
		}
		return true;
	}
	
	private boolean promtPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter password (Enter 'q' to use default password 'User@1234'): ");
		String password = sc.nextLine();
		if (password.equalsIgnoreCase("q")) {
			this.setPassword("User@1234");
			System.out.println("here");
			return true;
		}else {
			while (this.setPassword(password) != true) {
				System.out.println("Password should have lenght >= 8, and contain at least 1 uppercase character, 1 special character!");
				System.out.println("Please re-enter password: ");
				password = sc.nextLine();
			}
			return true;
		}
	}
	
	private boolean promtBirthday() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter birthday (yyyy-MM-dd): ");
		String birthday = sc.nextLine();
		while (this.setBirthday(birthday) != true) {
			System.out.println("Invalid day format! Please re-enter email (yyyy-MM-dd): ");
			birthday = sc.nextLine();
		}
		return true;
	}
	
	private boolean promtPhoneNumber() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter phone number: ");
		String tel = sc.nextLine();
		while (this.setPhoneNumber(tel) != true) {
			System.out.println("Invalid phone number format! Please re-enter phone number: ");
			tel = sc.nextLine();
		}
		return true;
	}
	
	private boolean promtAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter address: ");
		String address = sc.nextLine();
		while (this.setAddress(address) != true) {
			System.out.println("Invalid address format! Please re-enter address: ");
			address = sc.nextLine();
		}
		return true;
	}
	
	private boolean promtGender() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose gender: [0-male, 1-female]");
		int gender = sc.nextInt();
		while (gender != 0 || gender != 1) {
			System.out.println("Invalid gender! Please choose a gender: [0-male, 1-female]");
			gender = sc.nextInt();
		}
		this.setGender(gender);
		return true;
	}
	
	private String genID() {
		String temp = this.getCurrentDatetime();
		temp = temp.replace("-", "");
		temp = temp.replace(":", "");
		temp = temp.replace(" ", "");
		return temp;
	}
	
	public void promtDetail() {
		String ID = this.genID();
		this.ID = ID;
		this.promtName();
		this.promtEmail();
		this.promtPassword();
		this.promtBirthday();
		this.promtPhoneNumber();
		this.promtAddress();
		this.promtGender();
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
	
	public Student(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, int gender, String course, int year) throws Exception {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
		if (repOk(course, year)) {
			this.course = course;
			this.year = year;
		}
	}

	public Student() {
		
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
	
	public boolean promtCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter course: ");
		String course = sc.nextLine();
		while (this.setCourse(course) != true) {
			System.out.println("Invalid course! Please re-enter course: ");
			course = sc.nextLine();
		}
		return true;
	}
	
	public boolean promtYear() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter year: ");
		int year = sc.nextInt();
		while (this.setYear(year) != true) {
			System.out.println("Invalid year! Please re-enter year: ");
			year = sc.nextInt();
		}
		return true;
	}
	
	public void promtEducationDetail() {
		this.promtCourse();
		this.promtYear();
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

	@Override
	public String toString() {
		return super.toString() + ", course=" + course + ", year=" + year;
	}
	
}

class Admin extends User implements LibraryManagementTools, AdminTools {
	private ArrayList<Student> studentList;
	private ArrayList<Librarian> librarianList;
	private final int USER_TYPE_LIBRARIAN = 0;
	private final int USER_TYPE_STUDENT = 1;
	
	public Admin() {
		studentList = new ArrayList<Student>();
		librarianList = new ArrayList<Librarian>();
	}

	public Admin(String ID, String name, String email, String password, String birthday, String phoneNumber,
			String address, int gender) {
		super(ID, name, email, password, birthday, phoneNumber, address, gender);
		studentList = new ArrayList<Student>();
		librarianList = new ArrayList<Librarian>();
	}

	@Override
	public String createLibrarian() {
		Librarian lib = new Librarian();
		lib.promtDetail();
		this.librarianList.add(lib);
		return lib.getID();
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
	public String createStudent() {
		// TODO Auto-generated method stub
		return "";
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
	public String createRoom() {
		// TODO Auto-generated method stub
		return "";
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
	public String createLibrarian(); // return new Librarian ID
	public boolean removeLibrarian(int librarianID);
	public boolean updateLibrarian(int librarianID, Librarian librarian);
	public ArrayList<Librarian> getAllLibrarian();
	public String createStudent(); // return new Student ID
	public boolean removeStudent(int studentID);
	public boolean updateStudent(int studentID, Student student);
	public String createRoom(); // return new Room ID
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

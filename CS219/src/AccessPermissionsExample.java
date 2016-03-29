import java.util.ArrayList;

/***************************************************
 * AccessPermissionsExample.java
 * Paul Parker
 * <p>
 * Demonstrates the use of enumerated values and short
 * cuts
 ****************************************************/
public class AccessPermissionsExample {
    public static void main(String[] args) {
        ArrayList<Access> allDepartmentsRights = new ArrayList<Access>(); // contains only all department access
        ArrayList<Access> ownDepartmentRights = new ArrayList<Access>(); // contains only own department access
        ArrayList<Access> allAndOwnDepartmentRights = new ArrayList<Access>(); // contains both all and own department access
        ArrayList<Access> noRights = new ArrayList<Access>(); // contains no access rights
        String research = "Research";
        String development = "Development";
        String custodial = "Custodial";

    }

    /**
     * @param accessRights            access rights user has
     * @param userDepartment          department of the user
     * @param departmentBeingAccessed department being accessed
     */
    private static void testAccess(ArrayList<Access> accessRights, String userDepartment, String departmentBeingAccessed) {
        if (testAllAccess(accessRights) || (testOwnAccess(accessRights) && userDepartment.compareToIgnoreCase(departmentBeingAccessed) == 0)) {

        }
    }

    /**
     * @param accessRights access rights user has
     * @return whether the access list has all department access
     */
    private static boolean testAllAccess(ArrayList<Access> accessRights) {
        boolean hasAllAccess = accessRights.contains(Access.AllDepartments); // if user has all access
        if (hasAllAccess) {
            System.out.print("User had all access to departments and no further validation required");
        }
        return hasAllAccess;
    }

    private static boolean testSameDepartment (String userDepartment, String departmentBeingAccessed) {
        boolean isSameDepartment = userDepartment.compareToIgnoreCase(departmentBeingAccessed) == 0; // department matches
        if(isSameDepartment) {
            System.out.print("User did not have all access but does have own access and the department is the same");
        }
        return isSameDepartment;
    }

    /**
     *
     * @param accessRights access rights user has
     * @return whether user has access to own department
     */
    private static boolean testOwnAccess(ArrayList<Access> accessRights) {
        boolean hasOwnAccess = accessRights.contains(Access.OwnDepartment); // if user has own access
        if (!hasOwnAccess) {
            System.out.print("User does not have access to own department no further validation required");
        }
        return hasOwnAccess;
    }

    /**
     * Enum describing basic user access to departments
     */
    private enum Access {
        AllDepartments, OwnDepartment
    }
}

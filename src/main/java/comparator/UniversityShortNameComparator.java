package comparator;

import model.University;
import org.apache.commons.lang3.StringUtils;

public class UniversityShortNameComparator implements UniversityComparator{

    public int compare(University o1, University o2){
        return StringUtils.compare(o1.getShortName(), o2.getShortName());

    }
}

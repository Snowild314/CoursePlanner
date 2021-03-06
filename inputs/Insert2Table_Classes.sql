insert into Classes (
    --ClassNbr,
    --ClassPatNbr,
    --ClassCourseId,
    ClassComponent,
    ClassSection,
    ClassMtgStart,
    ClassMtgEnd,
    ClassMon,
    ClassTues,
    ClassWed,
    ClassThurs,
    ClassFri,
    --ClassTotEnrl,
    --ClassCapEnrl,
    ClassFacilID
    )
select

--ClassNbr,
--PatNbr,
--CourseID,
Component, Section,
       MtgStart, MtgEnd,
       Mon, Tues, Wed, Thurs, Fri,
       --TotEnrl, CapEnrl,
       --FacilID
       CASE
       WHEN FacilID = '' THEN 'TBA'
       ELSE FacilID
       END AS FacilID
from CoursePlannerBIGGEST
group by MtgStart, MtgEnd, Mon, Tues, Wed, Thurs, Fri, FacilID
order by MtgStart, MtgEnd, Mon, Tues, Wed, Thurs, Fri, FacilID;
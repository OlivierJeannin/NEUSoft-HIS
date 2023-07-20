# 东北大学（中国沈阳）"东软云医院"暑期实训项目

功能需求：
- 挂号、退号
- 医生填写患者病情、开立检查/检验/处方/处置等收费项目
- 收费、退费
- 药方发药、退药
- 医技执行检查、检验、处置等收费项目

*：患者病历、付费项目信息、疾病信息等，类似于数据库，暂时以类的方式处理，之后再转换到更灵活、具有特殊格式的数据文件。

---

This is a summer programming project hosted by Software College of Northeastern University, Shenyang, China.

The task is to create a Java program with GUI, that emulates the business of a hospital.

The hospital receives patients every day.

The first thing a patient will do is to register for a doctor, and get a history book from the registrar. The patient can be unregistered if they want.

After registering, the patient gets to see the doctor, who fills in a form in his/her history book, and decides which diseases the patient has got. If the doctor can't decide yet, a set of chemical tests and/or physical examinations will be added. Those items must be checked in order to continue the diagnosis.

Then the final diagnosis is given by the doctor according to results of examinations and tests. A list of medications and/or treatments is also added. The diagnosis is now over, and the patient goes to the pharmacy to get the medications.

*: Physical examinations, chemical tests, medications and treatments must be paid in order for patients to get them. Before these items are actually used, the patient may ask for a refund. A refund for medications, particularly, is performed after they are returned back to the pharmacy.
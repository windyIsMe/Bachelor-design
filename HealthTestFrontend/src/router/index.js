import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/login/Login')), 'login');

const doctorHome = r => require.ensure([], () => r(require('@/doctor/doctorHome')), 'doctorHome');
const dataList = r => require.ensure([], () => r(require('@/doctor/dataList')), 'dataList');
const getAppointment = r => require.ensure([], () => r(require('@/doctor/getAppointment')), 'getAppointment');
const sendAnnouncement = r => require.ensure([], () => r(require('@/doctor/sendAnnouncement')), 'sendAnnouncement');
const changeDoctorPassword = r => require.ensure([], () => r(require('@/doctor/changeDoctorPassword')), 'changeDoctorPassword');
const healthData = r => require.ensure([], () => r(require('@/doctor/healthData')), 'healthData');
const editData = r => require.ensure([], () => r(require('@/doctor/editData')), 'editData');

const examinerHome = r => require.ensure([], () => r(require('@/examiner/examinerHome')), 'examinerHome');
const personData = r => require.ensure([], () => r(require('@/examiner/personData')), 'personData');
const sendAppointment = r => require.ensure([], () => r(require('@/examiner/sendAppointment')), 'sendAppointment');
const getAnnouncement = r => require.ensure([], () => r(require('@/examiner/getAnnouncement')), 'getAnnouncement');
const changeExaminerPassword = r => require.ensure([], () => r(require('@/examiner/changeExaminerPassword')), 'changeExaminerPassword');




const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/doctorHome',
		component: doctorHome,
		children: [{
			path: '/dataList',
			component: dataList,
		},{
			path: '/getAppointment',
			component: getAppointment
		},{
			path: '/sendAnnouncement',
			component: sendAnnouncement
		},{
			path: '/changeDoctorPassword',
			component: changeDoctorPassword
		},{
			path: '/healthData',
			component: healthData
		},{
			path: '/editData',
			component: editData
		}]
	},
	{
		path: '/examinerHome',
		component: examinerHome,
		children: [{
			path: '/personData',
			component: personData
		},{
			path: '/sendAppointment',
			component: sendAppointment
		},{
			path: '/getAnnouncement',
			component: getAnnouncement
		},{
			path: '/changeExaminerPassword',
			component: changeExaminerPassword
		}]
	}
]

export default new Router({
	routes,
	strict: process.env.NODE_ENV !== 'production',
})

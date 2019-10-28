<template>
  <el-card>
    <div>
      <el-input
        placeholder="添加一个用户"
        style="width: 20%"
        v-model="newUser"
        clearable>
      </el-input>
      <el-button
        icon="el-icon-search"
        circle
        @click="searchUser()">
      </el-button>
      <el-button
        type="primary"
        icon="el-icon-edit"
        circle
        @click="addUser()">
      </el-button>
    </div>
    <el-table-column
      v-for="(user,index) in allUser"
      :key="index"
      width="180">
      <span>{{user.name}}</span>
      <el-input
        placeholder="编辑姓名"
        v-model="user.name"
        @click='saveUser(user)'
        @keyup.enter='editUser(user)'
        style="width: 20%"
        clearable>
      </el-input>
      <el-button
        type="danger"
        icon="el-icon-delete"
        circle
        @click='removeUser(user)'>
      </el-button>
    </el-table-column>
  </el-card>
</template>

<script>

export default {
  name: 'user-list',
  data: function () {
    return {
      newUser: '',
      allUser: [ ],
      editU: null
    }
  },
  created: function () {
    console.log('created')
    this.searchAll()
  },
  methods: {
    addUser: function () {
      var _this = this
      if (_this.newUser !== '') {
        _this.$axios.post('/' + _this.newUser)
          .then(successResponse => {
            console.log('addUser:' + successResponse.data)
            _this.searchAll()
          })
          .catch(failResponse => {
            console.log(failResponse)
          })
      }
      _this.newUser = ''
    },
    searchAll: function () {
      var _this = this
      _this.allUser = [ ]
      _this.$axios.get('/')
        .then(successResponse => {
          console.log(successResponse.data)
          for (var val in successResponse.data) {
            _this.allUser.push({name: successResponse.data[val].name})
          }
          console.log(_this.allUser.length)
        })
        .catch(failResponse => {
          console.log(failResponse)
        })
    },
    saveUser: function (user) {
      this.editU = user.name
      console.log(this.editU)
    },
    removeUser: function (user) {
      var _this = this
      _this.$axios.delete('/' + user.name)
        .then(successResponse => {
          console.log('removeUser:' + successResponse.data)
          _this.searchAll()
        })
        .catch(failResponse => {
          console.log(failResponse)
        })
    },
    searchUser: function () {
      var _this = this
      if (_this.newUser === '') {
        _this.searchAll()
      } else {
        _this.allUser = [ ]
        _this.$axios.get('/' + _this.newUser)
          .then(successResponse => {
            console.log(successResponse.data)
            _this.allUser.push({name: successResponse.data.name})
          })
          .catch(failResponse => {
            console.log(failResponse)
          })
      }
    },
    editUser: function (user) {
      var _this = this
      _this.$axios.put('/' + user.name + '/' + _this.editU)
        .then(successResponse => {
          console.log('editUser:' + successResponse.data)
        })
        .catch(failResponse => {
          console.log(failResponse)
        })
    }
  }
}
</script>
<style>

</style>

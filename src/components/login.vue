<template>
  <div id="login">
    <h1>Password Manager</h1>
    <input
      class="loginInput"
      type="email"
      name="email"
      v-model="input.email"
      placeholder="Email Address"
    />
    <br />
    <input
      class="loginInput"
      type="text"
      name="username"
      v-model="input.username"
      placeholder="Username"
    />
    <br />
    <input
      class="loginInput"
      type="password"
      name="password"
      v-model="input.password"
      placeholder="Password"
    />

    <br /><br />

    <button
      class="bt1"
      type="button"
      v-on:click="login(input.username, input.password, input.email)"
    >
      Login
    </button>
    &nbsp; &nbsp;
    <button
      class="bt1"
      type="button"
      v-on:click="register(input.username, input.password, input.email)"
    >
      Register
    </button>

    <br /><br /><br />
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      input: {
        username: "",
        password: "",
        newUsername: "",
        newPassword: "",
        email: "",
      },
    };
  },
  methods: {
    login(usrname, pass, eml) {
      let uri = "http://localhost:4023/login";
      var query = { username: usrname, password: pass, email: eml, userData: [{}] };
      this.axios.post(uri, query).then((response) => {
        if (response.data == true) {
          this.$emit("authenticated", true);
          this.$router.replace({ name: "secure" });
          this.$parent.infoAccount.username = usrname;
          this.$parent.infoAccount.password = pass;
          this.$parent.infoAccount.email = eml;
        } else {
          console.log("The username and / or password is incorrect");
          alert("The username and / or password is incorrect");
        }
      });
    },

    register(usrname, pass, eml) {
      let uri = "http://localhost:4023/register";

      var query = { username: usrname, password: pass, email: eml };
      this.axios
        .post(uri, query)
        .then(
          (response) => console.log(response.data),
          alert("Account Created, Login to access")
        );
    },
  },
};
</script>

<style scoped>
@import "../assets/style.css";
</style>

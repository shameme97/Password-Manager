<template>
  <div>
    <h1>Password Manager</h1>
    <div class="newadd">
      <input class="addInput" type="text" v-model="value" placeholder="Website URL" />
      <input class="addInput" type="text" v-model="value2" placeholder="Username" />
      <input class="addInput" type="text" v-model="value3" placeholder="Password" />
      <input class="addInput" type="text" v-model="value4" placeholder="Email address" />
      &nbsp;
      <button
        class="addButton"
        v-on:click="
          addItem(value, value2, value3, value4);
          fetchItems();
        "
      >
        ADD
      </button>
    </div>

    <table>
      <thead>
        <colgroup>
          <col span="1" style="width: 5%" />
          <col span="1" style="width: 20%" />
          <col span="1" style="width: 20%" />
          <col span="1" style="width: 25%" />
          <col span="1" style="width: 15%" />
          <col span="1" style="width: 5%" />
          <col span="1" style="width: 5%" />
          <col span="1" style="width: 5%" />
        </colgroup>
        <tr>
          <td>Sl.</td>
          <td>Website</td>
          <td>Username</td>
          <td>Email</td>
          <td>Password</td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </thead>

      <tbody>
        <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
        />
        <tr v-for="(item, index) in items" :key="item._id">
          <colgroup>
            <col span="1" style="width: 5%" />
            <col span="1" style="width: 20%" />
            <col span="1" style="width: 20%" />
            <col span="1" style="width: 25%" />
            <col span="1" style="width: 15%" />
            <col span="1" style="width: 5%" />
            <col span="1" style="width: 5%" />
            <col span="1" style="width: 5%" />
          </colgroup>

          <td>{{ index + 1 }}</td>
          <td>{{ item.website }}</td>
          <td>{{ item.websiteUsername }}</td>
          <td>{{ item.websiteEmail }}</td>
          <td>{{ item.websitePassword }}</td>
          <td>
            <button class="bt4" v-on:click="copyPassword(item.websitePassword)">
              <i class="fa fa-copy"></i>
            </button>
          </td>
          <td>
            <button v-on:click="updatePassword(item)" class="bt2">
              <i class="fa fa-edit"></i>
            </button>
          </td>

          <td>
            <button class="bt3" v-on:click="deleteWebsite(item, index)">
              <i class="fa fa-trash"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: "",
      value2: "",
      items: [],
      vsblt: true,
    };
  },
  components: {
    name: "AddItem",
  },

  created: function () {
    this.fetchItems();
  },

  methods: {
    fetchItems() {
      let uri = "http://localhost:4023/all/";

      uri =
        uri +
        this.$parent.infoAccount.username +
        "/" +
        this.$parent.infoAccount.password +
        "/" +
        this.$parent.infoAccount.email;
      this.axios.get(uri).then((response) => {
        this.items = response.data;
      });
    },

    addItem(webUrl, usrname, pass, email) {
      let uri =
        "http://localhost:4023/add/" +
        this.$parent.infoAccount.username +
        "/" +
        this.$parent.infoAccount.password +
        "/" +
        this.$parent.infoAccount.email;
      var body = {
        website: webUrl,
        websiteUsername: usrname,
        websitePassword: pass,
        websiteEmail: email,
      };

      this.axios.post(uri, body).then((response) => {
        console.log(response.data);

        this.fetchItems();
      });
    },

    deleteWebsite(item, index) {
      let uri =
        "http://localhost:4023/delete/" +
        this.$parent.infoAccount.username +
        "/" +
        this.$parent.infoAccount.password +
        "/" +
        this.$parent.infoAccount.email;

      this.axios
        .delete(uri, {
          data: {
            website: item.website,
            websiteUsername: item.websiteUsername,
            websitePassword: item.websitePassword,
            websiteEmail: item.websiteEmail,
          },
        })
        .then((response) => console.log(response.data));
      this.items.splice(index, 1);
    },

    async copyPassword(password) {
      await navigator.clipboard.writeText(password);
      alert("Password is Copied: " + password);
    },

    updatePassword(item) {
      let uri =
        "http://localhost:4023/update/" +
        this.$parent.infoAccount.username +
        "/" +
        this.$parent.infoAccount.password +
        "/" +
        this.$parent.infoAccount.email;
      var newPass = window.prompt("Enter your new Password for " + item.website + " : ");
      var body = {
        website: item.website,
        websiteUsername: item.websiteUsername,
        websitePassword: newPass,
        websiteEmail: item.websiteEmail,
      };

      this.axios.put(uri, body).then((response) => {
        console.log(response.data);
        item.wesitePassword = newPass;
        this.fetchItems();
      });
    },

    visibility() {
      if (this.vsblt == true) {
        this.vsblt = false;
      } else {
        this.vsblt = true;
      }
    },
  },
};
</script>

<style>
@import "../assets/SecureStyle.css";
</style>

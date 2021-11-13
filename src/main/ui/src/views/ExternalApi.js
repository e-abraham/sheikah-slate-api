import React, { useState } from "react";
import { Button, Alert } from "reactstrap";
import Highlight from "../components/Highlight";
import { useAuth0, withAuthenticationRequired } from "@auth0/auth0-react";
import { getConfig } from "../config";
import Loading from "../components/Loading";

export const ExternalApiComponent = () => {
  const { apiOrigin = "http://127.0.0.1:8080", audience } = getConfig();

  const [state, setState] = useState({
    showResult: false,
    apiMessage: "",
    error: null,
  });

  const {
    getAccessTokenSilently,
    loginWithPopup,
    getAccessTokenWithPopup,
  } = useAuth0();

  const handleConsent = async () => {
    try {
      await getAccessTokenWithPopup();
      setState({
        ...state,
        error: null,
      });
    } catch (error) {
      setState({
        ...state,
        error: error.error,
      });
    }

    await callApi();
  };

  const handleLoginAgain = async () => {
    try {
      await loginWithPopup();
      setState({
        ...state,
        error: null,
      });
    } catch (error) {
      setState({
        ...state,
        error: error.error,
      });
    }

    await callApi();
  };

  const callApi = async () => {
    try {
      const token = await getAccessTokenSilently();

      const response = await fetch(`${apiOrigin}/hyrule/users`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const responseData = await response.json();

      setState({
        ...state,
        showResult: true,
        apiMessage: responseData,
      });
    } catch (error) {
      setState({
        ...state,
        error: error.error,
      });
    }
  };

  const handle = (e, fn) => {
    e.preventDefault();
    fn();
  };

  return (
    <>
      <div className="mb-5">
        {state.error === "consent_required" && (
          <Alert color="warning">
            You need to{" "}
            <a
              href="#/"
              class="alert-link"
              onClick={(e) => handle(e, handleConsent)}
            >
              consent to get access to users api
            </a>
          </Alert>
        )}

        {state.error === "login_required" && (
          <Alert color="warning">
            You need to{" "}
            <a
              href="#/"
              class="alert-link"
              onClick={(e) => handle(e, handleLoginAgain)}
            >
              log in again
            </a>
          </Alert>
        )}

        <h1>Slate API</h1>
        <p className="lead">
          Ping an external API by clicking the button below. You will get a list of Hyrule's Residents.
        </p>
          The series centers on the various incarnations of Link, a courageous young Hylian man, with pointy elf-like ears; and Princess Zelda,
          a magical princess that is the mortal reincarnation of the goddess Hylia; as they fight to save the magical land of Hyrule from Ganon,
          an evil warlord turned demon king, who is the principal antagonist of the series.
        <p>

        </p>

        {!audience && (
          <Alert color="warning">
            <p>
              You can't call the API at the moment because your application does
              not have any configuration for <code>audience</code>, or it is
              using the default value of <code>YOUR_API_IDENTIFIER</code>. You
              might get this default value if you used the "Download Sample"
              feature of{" "}
              <a href="https://auth0.com/docs/quickstart/spa/react">
                the quickstart guide
              </a>
              , but have not set an API up in your Auth0 Tenant. You can find
              out more information on{" "}
              <a href="https://auth0.com/docs/api">setting up APIs</a> in the
              Auth0 Docs.
            </p>
            <p>
              The audience is the identifier of the API that you want to call
              (see{" "}
              <a href="https://auth0.com/docs/get-started/dashboard/tenant-settings#api-authorization-settings">
                API Authorization Settings
              </a>{" "}
              for more info).
            </p>

            <p>
              In this sample, you can configure the audience in a couple of
              ways:
            </p>
            <ul>
              <li>
                in the <code>src/index.js</code> file
              </li>
              <li>
                by specifying it in the <code>auth_config.json</code> file (see
                the <code>auth_config.json.example</code> file for an example of
                where it should go)
              </li>
            </ul>
            <p>
              Once you have configured the value for <code>audience</code>,
              please restart the app and try to use the "Ping API" button below.
            </p>
          </Alert>
        )}

        <Button
          color="primary"
          className="mt-5"
          onClick={callApi}
          disabled={!audience}
        >
          Hyrule Users
        </Button>
      </div>

      <div className="result-block-container">
        {state.showResult && (
          <div className="result-block" data-testid="api-result">
            <h6 className="muted">Result</h6>
            <Highlight>
              <span>{JSON.stringify(state.apiMessage, null, 2)}</span>
            </Highlight>
          </div>
        )}
      </div>
    </>
  );
};

export default withAuthenticationRequired(ExternalApiComponent, {
  onRedirecting: () => <Loading />,
});
